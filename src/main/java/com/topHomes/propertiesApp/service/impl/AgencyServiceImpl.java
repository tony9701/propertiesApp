package com.topHomes.propertiesApp.service.impl;

import com.topHomes.propertiesApp.model.dto.RegisterAgencyDTO;
import com.topHomes.propertiesApp.model.dto.RegisterUserDTO;
import com.topHomes.propertiesApp.model.entity.Agency;
import com.topHomes.propertiesApp.repository.AgencyRepository;
import com.topHomes.propertiesApp.service.AddressService;
import com.topHomes.propertiesApp.service.AgencyService;
import com.topHomes.propertiesApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository agencyRepository;
    private final ModelMapper modelMapper;
    private final AddressService addressService;
    private final UserService userService;

    public AgencyServiceImpl(AgencyRepository agencyRepository, ModelMapper modelMapper, AddressService addressService, UserService userService) {
        this.agencyRepository = agencyRepository;
        this.modelMapper = modelMapper;
        this.addressService = addressService;
        this.userService = userService;
    }

    @Override
    public void registerAgency(RegisterAgencyDTO registerAgencyDTO) {

        if (agencyRepository.count() <= 0) {

            //register admin user for the agency

        userService.registerUser(new RegisterUserDTO(
                    registerAgencyDTO.getEmail(),
                    registerAgencyDTO.getAgencyName(),
                    registerAgencyDTO.getAgencyName(),
                    registerAgencyDTO.getPassword()
            ));
        }

        if(agencyRepository.existsByEmail(registerAgencyDTO.getEmail())) {
            return; //TODO return error
        }

        agencyRepository.save(map(registerAgencyDTO));
    }

    private Agency map(RegisterAgencyDTO registerAgencyDTO) {
        Agency agency = modelMapper.map(registerAgencyDTO, Agency.class);
        agency.setAddress(addressService.getEmptyAddress());

        return agency;
    }
}
