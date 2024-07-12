package com.topHomes.propertiesApp.model;

import com.topHomes.propertiesApp.model.BaseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "photos")
public class PropertyPhoto extends BaseEntity {

    @NotBlank
    private String url;

    @ManyToOne(optional = false)
    private Property property;
}
