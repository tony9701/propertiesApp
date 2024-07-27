package com.topHomes.propertiesApp.model.entity;

import com.topHomes.propertiesApp.model.entity.BaseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "photos")
public class PropertyPhoto extends BaseEntity {

    @NotBlank
    private String url;

    @ManyToOne(optional = false)
    private Property property;


    public PropertyPhoto(String url, Property property) {
        this.url = url;
        this.property = property;
    }
}
