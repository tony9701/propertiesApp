package com.topHomes.propertiesApp.service;

import com.topHomes.propertiesApp.model.entity.Address;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {

    Address createAddress(String city, String country, String number, String street);
}
