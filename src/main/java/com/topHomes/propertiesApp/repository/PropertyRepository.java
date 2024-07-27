package com.topHomes.propertiesApp.repository;

import com.topHomes.propertiesApp.model.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    Property getByName(String propertyName);
}
