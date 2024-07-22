package com.topHomes.propertiesApp.repository;

import com.topHomes.propertiesApp.model.entity.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {
    boolean existsByEmail(String email);
}
