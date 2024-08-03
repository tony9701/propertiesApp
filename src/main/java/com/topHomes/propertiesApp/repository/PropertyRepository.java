package com.topHomes.propertiesApp.repository;

import com.topHomes.propertiesApp.model.entity.Property;
import com.topHomes.propertiesApp.model.enums.TransactionTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    Property getByName(String propertyName);

    List<Property> findAllByTransactionType(TransactionTypeEnum transactionTypeEnum);
}
