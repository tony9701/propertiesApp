package com.topHomes.propertiesApp.repository;

import com.topHomes.propertiesApp.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
