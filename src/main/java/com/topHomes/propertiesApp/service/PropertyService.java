package com.topHomes.propertiesApp.service;

import com.topHomes.propertiesApp.model.dto.AddPropertyDTO;
import com.topHomes.propertiesApp.model.entity.Property;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PropertyService {
    void addProperty(AddPropertyDTO addPropertyDTO);

    List<Property> getAllProperties();

    List<Property> getPropertiesBuy();

    List<Property> getPropertiesRent();
}
