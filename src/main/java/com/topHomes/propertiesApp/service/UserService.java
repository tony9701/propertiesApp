package com.topHomes.propertiesApp.service;

import com.topHomes.propertiesApp.model.dto.RegisterUserDTO;
import com.topHomes.propertiesApp.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void registerUser(RegisterUserDTO registerUserDTO);

    void registerAdmin();

    boolean isUserRepoEmpty();

    List<User> getAllUsers();
}
