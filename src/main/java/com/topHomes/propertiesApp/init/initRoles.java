package com.topHomes.propertiesApp.init;

import com.topHomes.propertiesApp.model.entity.UserRoles;
import com.topHomes.propertiesApp.model.enums.UserRolesEnum;
import com.topHomes.propertiesApp.repository.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class initRoles implements CommandLineRunner {

    private final UserRoleRepository userRoleRepository;

    public initRoles(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRoleRepository.count() == 0) {

            List<UserRolesEnum> userRolesEnums = Arrays.asList(
                    UserRolesEnum.USER,
                    UserRolesEnum.ADMIN,
                    UserRolesEnum.AGENT,
                    UserRolesEnum.AGENCY_ADMIN
            );


            userRolesEnums.forEach(
                    userRole -> {
                        userRoleRepository.save(new UserRoles(userRole));
                    }
            );
        }
    }
}
