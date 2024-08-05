package com.topHomes.propertiesApp.service.exception;

import com.topHomes.propertiesApp.model.dto.RegisterAgencyDTO;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }

}
