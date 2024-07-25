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
import com.topHomes.propertiesApp.service.AddressService;
import com.topHomes.propertiesApp.service.AgencyService;
import com.topHomes.propertiesApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository agencyRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AgencyServiceImpl(AgencyRepository agencyRepository, ModelMapper modelMapper, UserRepository userRepository, UserRoleRepository userRoleRepository, UserService userService, PasswordEncoder passwordEncoder) {
        this.agencyRepository = agencyRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerAgency(RegisterAgencyDTO registerAgencyDTO) {

        if(agencyRepository.existsByEmail(registerAgencyDTO.getEmail()) ||
            userRepository.existsByEmail(registerAgencyDTO.getEmail())) {
            return; //TODO return error
        }

        //create user with same credentials as the agency
        userService.registerUser(new RegisterUserDTO(
                    registerAgencyDTO.getEmail(),
                    registerAgencyDTO.getAgencyName(),
                    registerAgencyDTO.getAgencyName(),
                    registerAgencyDTO.getPassword()
            ));

        //get the new user(the agency) and set its roles to AGENCY_ADMIN
        User user = userRepository.findByEmail(registerAgencyDTO.getEmail()).get();
        List<UserRole> roles = new ArrayList<>(user.getRoles());
        UserRole role = userRoleRepository.findByRole(UserRoleEnum.AGENCY_ADMIN);
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);

        //save the agency in DB
        agencyRepository.save(map(registerAgencyDTO));
    }

    private Agency map(RegisterAgencyDTO registerAgencyDTO) {
        Agency agency = modelMapper.map(registerAgencyDTO, Agency.class);
        agency.setPassword(passwordEncoder.encode(registerAgencyDTO.getPassword()));

        return agency;
    }
}
