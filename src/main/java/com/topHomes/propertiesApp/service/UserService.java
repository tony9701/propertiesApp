package com.topHomes.propertiesApp.service;

import com.topHomes.propertiesApp.model.dto.RegisterUserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void registerUser(RegisterUserDTO registerUserDTO);


}
