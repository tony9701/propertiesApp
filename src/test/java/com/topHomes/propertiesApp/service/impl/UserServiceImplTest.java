package com.topHomes.propertiesApp.service.impl;

import com.topHomes.propertiesApp.model.dto.RegisterUserDTO;
import com.topHomes.propertiesApp.model.entity.User;
import com.topHomes.propertiesApp.model.entity.UserRole;
import com.topHomes.propertiesApp.model.enums.UserRoleEnum;
import com.topHomes.propertiesApp.model.user.PropertiesAppUserDetails;
import com.topHomes.propertiesApp.repository.UserRepository;
import com.topHomes.propertiesApp.repository.UserRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    private ModelMapper modelMapper = new ModelMapper();
    @Mock
    private UserRepository userRepository = mock(UserRepository.class);
    @Mock
    private PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    @Mock
    private UserRoleRepository userRoleRepository = mock(UserRoleRepository.class);
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userRepository, modelMapper, passwordEncoder, userRoleRepository);
    }


    // Register a new user with valid details
    @Test
    public void registerUser_test_with_valid_details() {

        RegisterUserDTO registerUserDTO = new RegisterUserDTO("test@example.com", "John", "Doe", "password123");
        UserRole userRole = new UserRole(UserRoleEnum.USER);

        when(userRepository.existsByEmail(registerUserDTO.getEmail())).thenReturn(false);
        when(userRoleRepository.findByRole(UserRoleEnum.USER)).thenReturn(userRole);
        when(passwordEncoder.encode(registerUserDTO.getPassword())).thenReturn("encodedPassword");

        userService.registerUser(registerUserDTO);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertEquals("test@example.com", savedUser.getEmail());
        assertEquals("encodedPassword", savedUser.getPassword());
        assertEquals(1, savedUser.getRoles().size());
        assertEquals(UserRoleEnum.USER, savedUser.getRoles().get(0).getRole());
    }

    @Test
    public void getAllUsers_retrieves_all_users() {
        User user1 = new User();
        user1.setEmail("user1@example.com");
        User user2 = new User();
        user2.setEmail("user2@example.com");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getAllUsers();

        assertEquals(2, users.size());
        assertEquals("user1@example.com", users.get(0).getEmail());
        assertEquals("user2@example.com", users.get(1).getEmail());
    }

    // Returns the current authenticated user when authentication is present
    @Test
    public void getCurrentUser_returns_current_authenticated_user() {
        // Arrange
        PropertiesAppUserDetails userDetails = new PropertiesAppUserDetails("test@example.com", "password", new ArrayList<>());
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Mockito.when(authentication.getPrincipal()).thenReturn(userDetails);
        User user = new User();
        user.setEmail("test@example.com");
        Mockito.when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.getCurrentUser();

        // Assert
        assertTrue(result.isPresent());
        Assertions.assertEquals("test@example.com", result.get().getEmail());
    }

    // Adds AGENT role to user and saves successfully
    @Test
    public void addRoleAndSave_test_add_agent_role_and_save_successfully() {

        User user = new User();
        UserRole userRole = new UserRole(UserRoleEnum.AGENT);
        when(userRoleRepository.findByRole(UserRoleEnum.AGENT)).thenReturn(userRole);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.addRoleAndSave(user);

        assertNotNull(result);
        assertTrue(result.getRoles().contains(userRole));
        verify(userRepository, times(1)).save(user);
    }

    // Successfully removes AGENT role from user
    @Test
    public void test_successfully_removes_agent_role() {

        User user = new User();
        UserRole agentRole = new UserRole(UserRoleEnum.AGENT);
        user.setRoles(List.of(agentRole));

        Mockito.when(userRoleRepository.findByRole(UserRoleEnum.AGENT)).thenReturn(agentRole);
        Mockito.when(userRepository.save(user)).thenReturn(user);

        User updatedUser = userService.deleteAgent(user);

        Assertions.assertFalse(updatedUser.getRoles().contains(agentRole));
        Assertions.assertNull(updatedUser.getAgency());
    }
}

