package com.topHomes.propertiesApp.service;

import com.topHomes.propertiesApp.model.entity.User;
import com.topHomes.propertiesApp.model.entity.UserRole;
import com.topHomes.propertiesApp.model.enums.UserRoleEnum;
import org.springframework.stereotype.Service;

@Service
public interface UserRoleService {

    void initializeUserRoles();

    boolean isUserRolesEmpty();

    UserRole findByRole(UserRoleEnum userRoleEnum);

}
