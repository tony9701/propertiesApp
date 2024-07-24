package com.topHomes.propertiesApp.repository;

import com.topHomes.propertiesApp.model.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoles, Long> {
}
