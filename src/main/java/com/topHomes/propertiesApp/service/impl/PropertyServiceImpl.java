package com.topHomes.propertiesApp.service.impl;

import com.topHomes.propertiesApp.model.dto.AddPropertyDTO;
import com.topHomes.propertiesApp.model.entity.Property;
import com.topHomes.propertiesApp.repository.PropertyRepository;
import com.topHomes.propertiesApp.service.PropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final ModelMapper modelMapper;

    public PropertyServiceImpl(PropertyRepository propertyRepository, ModelMapper modelMapper) {
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addProperty(AddPropertyDTO addPropertyDTO) {
        propertyRepository.save(map(addPropertyDTO));
    }

    private Property map(AddPropertyDTO addPropertyDTO) {
        Property property = modelMapper.map(addPropertyDTO, Property.class);
        return property;
    }
}
