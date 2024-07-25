package com.topHomes.propertiesApp.service.impl;

import com.topHomes.propertiesApp.model.dto.RegisterUserDTO;
import com.topHomes.propertiesApp.model.entity.User;
import com.topHomes.propertiesApp.repository.AddressRepository;
import com.topHomes.propertiesApp.repository.UserRepository;
import com.topHomes.propertiesApp.service.AddressService;
import com.topHomes.propertiesApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void registerUser(RegisterUserDTO registerUserDTO) {
        //check if email already exist
        if (userRepository.existsByEmail(registerUserDTO.getEmail())) {
         return; //TODO send error
        }

        userRepository.save(map(registerUserDTO));

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
    }

    private User map(RegisterUserDTO registerUserDTO) {
        User mappedEntity = modelMapper.map(registerUserDTO, User.class);

        mappedEntity.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));

        return mappedEntity;
    }
}

