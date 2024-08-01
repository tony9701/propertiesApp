package com.topHomes.propertiesApp.service.impl;

import com.topHomes.propertiesApp.model.dto.RegisterUserDTO;
import com.topHomes.propertiesApp.model.entity.User;
import com.topHomes.propertiesApp.model.entity.UserRole;
import com.topHomes.propertiesApp.model.enums.UserRoleEnum;
import com.topHomes.propertiesApp.model.user.PropertiesAppUserDetails;
import com.topHomes.propertiesApp.repository.UserRepository;
import com.topHomes.propertiesApp.repository.UserRoleRepository;
import com.topHomes.propertiesApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }


    @Override
    public void registerUser(RegisterUserDTO registerUserDTO) {
        //check if email already exist
        if (userRepository.existsByEmail(registerUserDTO.getEmail())) {
         return; //TODO send error
        }

        User user = map(registerUserDTO);
        UserRole role = userRoleRepository.findByRole(UserRoleEnum.USER);
        user.setRoles(List.of(role));

        userRepository.save(user);

    }

    @Override
    public void registerAdmin() {

        if (userRepository.count() == 0) {

            registerUser(new RegisterUserDTO(
                    "admin@admin.com",
                    "admin",
                    "admin",
                    "admin"
            ));
        }

        //get the new user(admin) and add the ADMIN role
        User user = userRepository.findByEmail("admin@admin.com").get();
        List<UserRole> roles = new ArrayList<>(user.getRoles());
        roles.add(userRoleRepository.findByRole(UserRoleEnum.ADMIN));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public boolean isUserRepoEmpty() {
        return userRepository.count() <= 0;
    }

    @Override
    public List<User> getAllUsers() {
        //TODO make it pageable in future
        return new ArrayList<>(userRepository.findAll());
    }

    @Override
    public Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String username = ((PropertiesAppUserDetails) principal).getUsername();
        return userRepository.findByEmail(username);
    }

    private User map(RegisterUserDTO registerUserDTO) {
        User mappedEntity = modelMapper.map(registerUserDTO, User.class);

        mappedEntity.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));

        return mappedEntity;
    }
}

