package com.topHomes.propertiesApp.service.impl;

import com.topHomes.propertiesApp.model.dto.AddAgentDTO;
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
import com.topHomes.propertiesApp.service.exception.ObjectNotFoundException;
import com.topHomes.propertiesApp.service.exception.UserAlreadyExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

        //TODO CATCH EXCEPTION
        if(agencyRepository.existsByEmail(registerAgencyDTO.getEmail()) ||
            userRepository.existsByEmail(registerAgencyDTO.getEmail())) {
            throw new UserAlreadyExistsException("User with that email already exists");
        }

        //create user with same credentials as the agency
        userService.registerUser(new RegisterUserDTO(
                    registerAgencyDTO.getEmail(),
                    registerAgencyDTO.getAgencyName(),
                    registerAgencyDTO.getAgencyName(),
                    registerAgencyDTO.getPassword()
            ));


        //save the agency in DB
        Agency agency = agencyRepository.save(map(registerAgencyDTO));

        //get the new user(the agency) and set its roles to AGENCY_ADMIN and set the Agency
        User user = userRepository.findByEmail(registerAgencyDTO.getEmail()).get();
        List<UserRole> roles = new ArrayList<>(user.getRoles());
        UserRole role = userRoleRepository.findByRole(UserRoleEnum.AGENCY_ADMIN);
        roles.add(role);
        user.setRoles(roles);
        user.setAgency(agency);
        userRepository.save(user);
    }

    @Override
    public List<Agency> getAllAgencies() {
        return agencyRepository.findAll();
    }

    @Override
    public Agency getAgencyById(Long id) {
        return agencyRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Agency not found"));
    }

    @Override
    public void addAgent(AddAgentDTO addAgentDTO) {
        //get user with email or throw if user is not registered
        User user = userService.getUserByEmail(addAgentDTO.getEmail())
                .orElseThrow(() -> new ObjectNotFoundException("The agent you want to add has to be already registered as a user!"));

        //check if that user is not already an agent or agent admin
        if (user.getRoles().stream().anyMatch(r -> r.getRole().equals(UserRoleEnum.AGENT)) ||
                user.getRoles().stream().anyMatch(r -> r.getRole().equals(UserRoleEnum.AGENCY_ADMIN))) {
            throw new UserAlreadyExistsException("The user is already an AGENT");
        } else if (user.getRoles().stream().anyMatch(r -> r.getRole().equals(UserRoleEnum.ADMIN))) {
            throw new UserAlreadyExistsException("You cannot add an ADMIN to the Agency");
        }

        //get current agency
        User currentUser = userService.getCurrentUser().get();
        Agency agency = currentUser.getAgency();

        //set AGENCY and  add role "AGENT"
        user.setAgency(agency);
        userService.addRoleAndSave(user);
    }

    private Agency map(RegisterAgencyDTO registerAgencyDTO) {
        Agency agency = modelMapper.map(registerAgencyDTO, Agency.class);
        agency.setPassword(passwordEncoder.encode(registerAgencyDTO.getPassword()));

        return agency;
    }
}
