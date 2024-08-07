package com.topHomes.propertiesApp.service.impl;

import com.topHomes.propertiesApp.model.entity.UserRole;
import com.topHomes.propertiesApp.model.enums.UserRoleEnum;
import com.topHomes.propertiesApp.repository.UserRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class UserRoleServiceImplTest {

    @Mock
    UserRoleRepository userRoleRepository = Mockito.mock(UserRoleRepository.class);
    private UserRoleServiceImpl userRoleService;


    @BeforeEach
    public void setUp() {
        userRoleService = new UserRoleServiceImpl(userRoleRepository);
    }

    // Returns true when userRoleRepository.count() is zero
    @Test
    public void isUserRolesEmpty_returns_true_when_count_is_zero() {

        Mockito.when(userRoleRepository.count()).thenReturn(0L);

        boolean result = userRoleService.isUserRolesEmpty();

        Assertions.assertTrue(result);
    }

    // findByRole returns correct UserRole for given UserRoleEnum
    @Test
    public void test_findByRole_returns_correct_UserRole() {
        UserRoleEnum roleEnum = UserRoleEnum.ADMIN;
        UserRole expectedUserRole = new UserRole(roleEnum);

        Mockito.when(userRoleRepository.findByRole(roleEnum)).thenReturn(expectedUserRole);

        UserRole actualUserRole = userRoleService.findByRole(roleEnum);

        Assertions.assertEquals(expectedUserRole, actualUserRole);
    }
}
