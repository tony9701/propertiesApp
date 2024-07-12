package com.topHomes.propertiesApp.model;

import com.topHomes.propertiesApp.model.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Address extends BaseEntity {

    @NotBlank
    private String country;

    @NotBlank
    private String city;

    @NotBlank
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
