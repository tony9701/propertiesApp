package com.topHomes.propertiesApp.repository;

import com.topHomes.propertiesApp.model.entity.PropertyPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyPhotoRepository extends JpaRepository<PropertyPhoto, Long> {
}
