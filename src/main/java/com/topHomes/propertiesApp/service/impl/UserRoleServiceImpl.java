package com.topHomes.propertiesApp.service.impl;

import com.topHomes.propertiesApp.model.entity.User;
import com.topHomes.propertiesApp.model.entity.UserRole;
import com.topHomes.propertiesApp.model.enums.UserRoleEnum;
import com.topHomes.propertiesApp.repository.UserRoleRepository;
import com.topHomes.propertiesApp.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void initializeUserRoles() {

        if (userRoleRepository.count() == 0) {

            List<UserRoleEnum> userRoleEnums = Arrays.asList(
                    UserRoleEnum.USER,
                    UserRoleEnum.ADMIN,
                    UserRoleEnum.AGENT,
                    UserRoleEnum.AGENCY_ADMIN
            );


            userRoleEnums.forEach(
                    userRole -> {
                        userRoleRepository.save(new UserRole(userRole));
                    }
            );
        }
    }

    @Override
    public boolean isUserRolesEmpty() {
        return userRoleRepository.count() <= 0;
    }

    @Override
    public UserRole findByRole(UserRoleEnum userRoleEnum) {
        return userRoleRepository.findByRole(userRoleEnum);
    }
}
