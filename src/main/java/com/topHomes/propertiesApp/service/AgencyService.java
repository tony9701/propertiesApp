package com.topHomes.propertiesApp.service;

import com.topHomes.propertiesApp.model.dto.RegisterAgencyDTO;
import com.topHomes.propertiesApp.model.entity.Agency;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AgencyService {
    void registerAgency(RegisterAgencyDTO registerAgencyDTO);

    List<Agency> getAllAgencies();
}
