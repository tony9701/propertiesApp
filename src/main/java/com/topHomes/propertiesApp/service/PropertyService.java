package com.topHomes.propertiesApp.service;

import com.topHomes.propertiesApp.model.dto.AddPropertyDTO;
import org.springframework.stereotype.Service;

@Service
public interface PropertyService {
    void addProperty(AddPropertyDTO addPropertyDTO);
}
