package com.topHomes.propertiesApp.service.impl;

import com.topHomes.propertiesApp.model.entity.Address;
import com.topHomes.propertiesApp.repository.AddressRepository;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class AddressServiceImplTest {

    // createAddress saves a valid address to the repository
    @Test
    public void should_save_valid_address_to_repository() {
        // Given
        AddressRepository addressRepository = mock(AddressRepository.class);
        AddressServiceImpl addressService = new AddressServiceImpl(addressRepository);
        Address validAddress = new Address("Test City", "Test Country", "123", "Test Street");

        // When
        when(addressRepository.save(any(Address.class))).thenReturn(validAddress);
        Address savedAddress = addressService.createAddress("Test City", "Test Country", "123", "Test Street");

        // Then
        verify(addressRepository, times(1)).save(any(Address.class));
        assertEquals("Test City", savedAddress.getCity());
        assertEquals("Test Country", savedAddress.getCountry());
        assertEquals("123", savedAddress.getNumber());
        assertEquals("Test Street", savedAddress.getStreet());
    }
}
