package com.topHomes.propertiesApp.service.impl;

import com.topHomes.propertiesApp.model.entity.Property;
import com.topHomes.propertiesApp.model.entity.PropertyPhoto;
import com.topHomes.propertiesApp.model.enums.PropertyTypeEnum;
import com.topHomes.propertiesApp.model.enums.TransactionTypeEnum;
import com.topHomes.propertiesApp.repository.PropertyPhotoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class PropertyPhotoServiceImplTest {

    private PropertyPhotoServiceImpl propertyPhotoService;
    @Mock
    private PropertyPhotoRepository mockRepository = Mockito.mock(PropertyPhotoRepository.class);

    @BeforeEach
    public void setUp() {
        propertyPhotoService = new PropertyPhotoServiceImpl(mockRepository);
    }

    // Successfully creates a PropertyPhoto with valid Property and URL
    @Test
    public void test_create_photo_with_valid_property_and_url() {



        Property property = new Property();
        property.setName("Test Property");
        property.setPropertyTypeEnum(PropertyTypeEnum.APARTMENT);
        property.setDescription("A beautiful apartment");
        property.setTransactionType(TransactionTypeEnum.RENT);
        property.setPrice(1000.0);
        property.setSize(100.0);

        String url = "http://example.com/photo.jpg";

        PropertyPhoto photo = new PropertyPhoto(url, property);
        Mockito.when(mockRepository.save(Mockito.any(PropertyPhoto.class))).thenReturn(photo);

        PropertyPhoto result = propertyPhotoService.createPhoto(property, url);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(url, result.getUrl());
        Assertions.assertEquals(property, result.getProperty());
    }
}
