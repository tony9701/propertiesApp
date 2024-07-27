package com.topHomes.propertiesApp.service.impl;

import com.topHomes.propertiesApp.model.entity.Address;
import com.topHomes.propertiesApp.repository.AddressRepository;
import com.topHomes.propertiesApp.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @Override
    public Address createAddress(String city, String country, String number, String street) {
        return addressRepository.save(new Address(city, country, number, street));
    }
}
