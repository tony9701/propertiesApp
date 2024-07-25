package com.topHomes.propertiesApp.init;

import com.topHomes.propertiesApp.model.entity.UserRole;
import com.topHomes.propertiesApp.model.enums.UserRolesEnum;
import com.topHomes.propertiesApp.repository.UserRoleRepository;
import com.topHomes.propertiesApp.service.UserRoleService;
import com.topHomes.propertiesApp.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class initRoles implements CommandLineRunner {

    private final UserService userService;
    private final UserRoleService userRoleService;

    public initRoles(UserService userService,  UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @Override
    public void run(String... args) throws Exception {

        //initialize user roles when the app starts for the first time
        initializeRoles();
        //initialize admin
        initializeAdmin();

    }

    private void initializeAdmin() {
        userService.registerAdmin();
    }


    private void initializeRoles() {
        userRoleService.initializeUserRoles();
    }
}
