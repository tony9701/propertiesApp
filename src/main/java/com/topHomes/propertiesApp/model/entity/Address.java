package com.topHomes.propertiesApp.model.entity;

import com.topHomes.propertiesApp.model.entity.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "addresses")
public class Address extends BaseEntity {

    @NotBlank
    @Size(min = 3, max = 50)
    private String country;

    @NotBlank
    @Size(min = 3, max = 50)
    private String city;

    @NotBlank
    @Size(min = 3, max = 150)
    private String street;

    @NotBlank
    private String number;

    private String postalCode;

    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
    private List<User> users;

    @OneToOne(mappedBy = "address")
    private Property property;

    @OneToOne(mappedBy = "address")
    private Agency agency;

    public Address() {
        this.users = new ArrayList<>();
    }

}
