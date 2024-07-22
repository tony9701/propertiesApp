package com.topHomes.propertiesApp.service;

import com.topHomes.propertiesApp.model.dto.RegisterAgencyDTO;
import org.springframework.stereotype.Service;

@Service
public interface AgencyService {
    void registerAgency(RegisterAgencyDTO registerAgencyDTO);
}
