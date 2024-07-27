package com.topHomes.propertiesApp.service;

import com.topHomes.propertiesApp.model.entity.Property;
import com.topHomes.propertiesApp.model.entity.PropertyPhoto;
import org.springframework.stereotype.Service;

@Service
public interface PropertyPhotoService {

    PropertyPhoto createPhoto(Property property, String url);
}
