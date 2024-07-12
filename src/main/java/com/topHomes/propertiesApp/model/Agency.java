package com.topHomes.propertiesApp.model;

import com.topHomes.propertiesApp.model.BaseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "agencies")
public class Agency extends BaseEntity {

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @OneToOne()
    private Address address;

    @NotBlank
    private String number;

    @Email
    @NotBlank
    private String email;

    @OneToMany(mappedBy = "agency", fetch = FetchType.EAGER)
    private List<Booking> bookings;

    @OneToMany(mappedBy = "agency", fetch = FetchType.EAGER)
    private List<Property> properties;

    public Agency() {
        this.bookings = new ArrayList<>();
        this.properties = new ArrayList<>();
    }
}
