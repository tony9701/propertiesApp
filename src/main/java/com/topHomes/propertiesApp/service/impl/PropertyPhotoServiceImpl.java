package com.topHomes.propertiesApp.service.impl;

import com.topHomes.propertiesApp.model.dto.PropertyPhotoDTO;
import com.topHomes.propertiesApp.model.entity.Property;
import com.topHomes.propertiesApp.model.entity.PropertyPhoto;
import com.topHomes.propertiesApp.repository.PropertyPhotoRepository;
import com.topHomes.propertiesApp.service.PropertyPhotoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PropertyPhotoServiceImpl implements PropertyPhotoService {

    private final PropertyPhotoRepository propertyPhotoRepository;

    public PropertyPhotoServiceImpl(PropertyPhotoRepository propertyPhotoRepository) {
        this.propertyPhotoRepository = propertyPhotoRepository;
    }

    @Override
    public PropertyPhoto createPhoto(Property property, String url) {
        //create photo and set property for the photo
        return propertyPhotoRepository.save(new PropertyPhoto(url, property));
    }
}
