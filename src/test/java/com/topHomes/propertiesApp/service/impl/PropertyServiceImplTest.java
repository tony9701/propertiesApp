package com.topHomes.propertiesApp.service.impl;

import com.topHomes.propertiesApp.model.dto.AddPropertyDTO;
import com.topHomes.propertiesApp.model.entity.Property;
import com.topHomes.propertiesApp.model.entity.PropertyPhoto;
import com.topHomes.propertiesApp.model.enums.PropertyTypeEnum;
import com.topHomes.propertiesApp.model.enums.TransactionTypeEnum;
import com.topHomes.propertiesApp.repository.PropertyRepository;
import com.topHomes.propertiesApp.service.AddressService;
import com.topHomes.propertiesApp.service.PropertyPhotoService;
import com.topHomes.propertiesApp.service.PropertyService;
import com.topHomes.propertiesApp.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceImplTest {

    @Mock
    private PropertyRepository propertyRepository;

    @Mock
    private PropertyPhotoService propertyPhotoService;

    @InjectMocks
    private PropertyServiceImpl propertyService;
    private ModelMapper modelMapper = new ModelMapper();
    private AddPropertyDTO addPropertyDTO;

    @BeforeEach
    void setUp() {
        propertyService = new PropertyServiceImpl(propertyRepository, modelMapper, null, null, propertyPhotoService, null);

        addPropertyDTO = new AddPropertyDTO();
        addPropertyDTO.setPhotoUrl("http://example.com/photo.jpg");
    }

    // Returns a list of all properties when properties exist in the repository
    @Test
    public void test_get_all_properties_when_properties_exist() {

        List<Property> properties = new ArrayList<>();
        properties.add(new Property());
        properties.add(new Property());

        when(propertyRepository.findAll()).thenReturn(properties);

        List<Property> result = propertyService.getAllProperties();

        Assertions.assertEquals(2, result.size());
    }

    // Handles the scenario where the PropertyRepository is empty
    @Test
    public void test_get_all_properties_when_repository_is_empty() {
        when(propertyRepository.findAll()).thenReturn(new ArrayList<>());

        List<Property> result = propertyService.getAllProperties();

        Assertions.assertTrue(result.isEmpty());
    }

    // Returns a list of properties with transaction type SELL
    @Test
    public void test_returns_properties_with_transaction_type_sell() {

        List<Property> properties = new ArrayList<>();
        Property property1 = new Property();
        property1.setTransactionType(TransactionTypeEnum.SELL);
        properties.add(property1);

        when(propertyRepository.findAllByTransactionType(TransactionTypeEnum.SELL)).thenReturn(properties);

        List<Property> result = propertyService.getPropertiesBuy();

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(TransactionTypeEnum.SELL, result.get(0).getTransactionType());
    }

    // Retrieves a list of properties with transaction type RENT
    @Test
    public void test_retrieves_properties_with_transaction_type_rent() {

        List<Property> expectedProperties = new ArrayList<>();
        expectedProperties.add(new Property());
        expectedProperties.add(new Property());

        when(propertyRepository.findAllByTransactionType(TransactionTypeEnum.RENT)).thenReturn(expectedProperties);

        List<Property> actualProperties = propertyService.getPropertiesRent();

        Assertions.assertEquals(expectedProperties.size(), actualProperties.size());
        Assertions.assertEquals(expectedProperties, actualProperties);
    }

}
