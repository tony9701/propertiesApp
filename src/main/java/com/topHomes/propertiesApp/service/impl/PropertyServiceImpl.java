package com.topHomes.propertiesApp.service.impl;

import com.topHomes.propertiesApp.model.dto.AddPropertyDTO;
import com.topHomes.propertiesApp.model.entity.*;

import com.topHomes.propertiesApp.model.enums.TransactionTypeEnum;
import com.topHomes.propertiesApp.model.user.PropertiesAppUserDetails;
import com.topHomes.propertiesApp.repository.AddressRepository;
import com.topHomes.propertiesApp.repository.PropertyRepository;
import com.topHomes.propertiesApp.repository.UserRepository;
import com.topHomes.propertiesApp.service.AddressService;
import com.topHomes.propertiesApp.service.PropertyPhotoService;
import com.topHomes.propertiesApp.service.PropertyService;
import com.topHomes.propertiesApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final ModelMapper modelMapper;
    private final AddressService addressService;
    private final PropertyPhotoService propertyPhotoService;
    private final UserService userService;

    public PropertyServiceImpl(PropertyRepository propertyRepository, ModelMapper modelMapper, AddressRepository addressRepository, AddressService addressService, PropertyPhotoService propertyPhotoService, UserService userService) {
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
        this.addressService = addressService;
        this.propertyPhotoService = propertyPhotoService;
        this.userService = userService;
    }

    @Override
    public void addProperty(AddPropertyDTO addPropertyDTO) {
        Property property = propertyRepository.save(map(addPropertyDTO));

        //create photo and set it in the property
        PropertyPhoto photo = propertyPhotoService.createPhoto(property, addPropertyDTO.getPhotoUrl());
        property.addPropertyPhoto(photo);
        propertyRepository.save(property);
    }

    @Override
    public List<Property> getAllProperties() {
       return propertyRepository.findAll();
    }

    @Override
    public List<Property> getPropertiesBuy() {
        return propertyRepository.findAllByTransactionType(TransactionTypeEnum.SELL);
    }

    @Override
    public List<Property> getPropertiesRent() {
        return propertyRepository.findAllByTransactionType(TransactionTypeEnum.RENT);
    }

    public Property map(AddPropertyDTO addPropertyDTO) {
        Property property = modelMapper.map(addPropertyDTO, Property.class);

        //create address
        Address address = addressService.createAddress(addPropertyDTO.getCity(),
                addPropertyDTO.getCountry(),
                addPropertyDTO.getNumber(),
                addPropertyDTO.getStreet());

        property.setAddress(address);

        User user = userService.getCurrentUser().get();

        boolean isAgent = user.getRoles().stream().anyMatch(role -> role.getRole().name().equals("AGENT"));
        boolean isAgentAdmin = user.getRoles().stream().anyMatch(role -> role.getRole().name().equals("AGENCY_ADMIN"));
        boolean isAdmin = user.getRoles().stream().anyMatch(role -> role.getRole().name().equals("ADMIN"));

        //check if the user is admin, agent or agency-admin
        if (isAgent || isAgentAdmin || isAdmin) {
            Agency agency = user.getAgency();
            property.setAgency(agency);
            property.setUser(user);
        }

        return property;
    }
}
