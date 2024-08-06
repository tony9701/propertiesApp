package com.topHomes.propertiesApp.service.impl;

import com.topHomes.propertiesApp.model.dto.RegisterAgencyDTO;
import com.topHomes.propertiesApp.model.dto.RegisterUserDTO;
import com.topHomes.propertiesApp.model.entity.Agency;
import com.topHomes.propertiesApp.model.entity.User;
import com.topHomes.propertiesApp.model.entity.UserRole;
import com.topHomes.propertiesApp.model.enums.UserRoleEnum;
import com.topHomes.propertiesApp.repository.AgencyRepository;
import com.topHomes.propertiesApp.repository.UserRepository;
import com.topHomes.propertiesApp.repository.UserRoleRepository;
import com.topHomes.propertiesApp.service.UserService;
import com.topHomes.propertiesApp.service.exception.UserAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AgencyServiceImplTest {

    // Register a new agency successfully
    @Test
    public void register_agency_successfully() {
        AgencyRepository agencyRepository = mock(AgencyRepository.class);
        ModelMapper modelMapper = new ModelMapper();
        UserRepository userRepository = mock(UserRepository.class);
        UserRoleRepository userRoleRepository = mock(UserRoleRepository.class);
        UserService userService = mock(UserService.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

        AgencyServiceImpl agencyService = new AgencyServiceImpl(
                agencyRepository, modelMapper, userRepository, userRoleRepository, userService, passwordEncoder
        );

        RegisterAgencyDTO registerAgencyDTO = new RegisterAgencyDTO();
        registerAgencyDTO.setEmail("test@agency.com");
        registerAgencyDTO.setAgencyName("Test Agency");
        registerAgencyDTO.setPassword("password");
        registerAgencyDTO.setConfirmPassword("password");

        when(agencyRepository.existsByEmail(registerAgencyDTO.getEmail())).thenReturn(false);
        when(userRepository.existsByEmail(registerAgencyDTO.getEmail())).thenReturn(false);

        doNothing().when(userService).registerUser(any(RegisterUserDTO.class));

        Agency agency = new Agency();
        agency.setEmail(registerAgencyDTO.getEmail());
        when(agencyRepository.save(any(Agency.class))).thenReturn(agency);

        User user = new User();
        user.setEmail(registerAgencyDTO.getEmail());
        when(userRepository.findByEmail(registerAgencyDTO.getEmail())).thenReturn(Optional.of(user));

        UserRole role = new UserRole(UserRoleEnum.AGENCY_ADMIN);
        when(userRoleRepository.findByRole(UserRoleEnum.AGENCY_ADMIN)).thenReturn(role);

        agencyService.registerAgency(registerAgencyDTO);

        verify(userService).registerUser(any(RegisterUserDTO.class));
        verify(agencyRepository).save(any(Agency.class));
        verify(userRepository).save(any(User.class));
    }

    // Register an agency with an email that already exists
    @Test
    public void register_agency_email_exists() {
        AgencyRepository agencyRepository = mock(AgencyRepository.class);
        ModelMapper modelMapper = new ModelMapper();
        UserRepository userRepository = mock(UserRepository.class);
        UserRoleRepository userRoleRepository = mock(UserRoleRepository.class);
        UserService userService = mock(UserService.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

        AgencyServiceImpl agencyService = new AgencyServiceImpl(
                agencyRepository, modelMapper, userRepository, userRoleRepository, userService, passwordEncoder
        );

        RegisterAgencyDTO registerAgencyDTO = new RegisterAgencyDTO();
        registerAgencyDTO.setEmail("test@agency.com");
        registerAgencyDTO.setAgencyName("Test Agency");
        registerAgencyDTO.setPassword("password");
        registerAgencyDTO.setConfirmPassword("password");

        when(agencyRepository.existsByEmail(registerAgencyDTO.getEmail())).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> {
            agencyService.registerAgency(registerAgencyDTO);
        });

        verify(userService, never()).registerUser(any(RegisterUserDTO.class));
        verify(agencyRepository, never()).save(any(Agency.class));
    }

    // Retrieve all agencies
    @Test
    public void retrieve_all_agencies_successfully() {
        // Given
        List<Agency> expectedAgencies = new ArrayList<>();
        expectedAgencies.add(new Agency());
        expectedAgencies.add(new Agency());

        AgencyRepository agencyRepository = mock(AgencyRepository.class);
        when(agencyRepository.findAll()).thenReturn(expectedAgencies);

        AgencyServiceImpl agencyService = new AgencyServiceImpl(agencyRepository, new ModelMapper(), mock(UserRepository.class), mock(UserRoleRepository.class), mock(UserService.class), mock(PasswordEncoder.class));

        // When
        List<Agency> actualAgencies = agencyService.getAllAgencies();

        // Then
        assertEquals(expectedAgencies.size(), actualAgencies.size());
        verify(agencyRepository, times(1)).findAll();
    }
    // Make sure to import the necessary classes for the test
}
