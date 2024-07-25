package com.topHomes.propertiesApp.service.impl;

import com.topHomes.propertiesApp.model.entity.UserRole;
import com.topHomes.propertiesApp.model.enums.UserRolesEnum;
import com.topHomes.propertiesApp.repository.UserRoleRepository;
import com.topHomes.propertiesApp.service.UserRoleService;
import org.springframework.stereotype.Service;

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

            List<UserRolesEnum> userRolesEnums = Arrays.asList(
                    UserRolesEnum.USER,
                    UserRolesEnum.ADMIN,
                    UserRolesEnum.AGENT,
                    UserRolesEnum.AGENCY_ADMIN
            );


            userRolesEnums.forEach(
                    userRole -> {
                        userRoleRepository.save(new UserRole(userRole));
                    }
            );
        }
    }
}
