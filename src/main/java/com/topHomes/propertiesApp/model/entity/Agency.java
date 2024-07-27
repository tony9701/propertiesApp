package com.topHomes.propertiesApp.model.entity;

import com.topHomes.propertiesApp.model.entity.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "agencies")
public class Agency extends BaseEntity {

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(unique = true)
    private String name;

    @NotBlank
    @Size(min = 6)
    private String password;

    @OneToOne()
    private Address address;

    @NotBlank
    private String number;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "agency", fetch = FetchType.EAGER)
    private List<Booking> bookings;

    @OneToMany(mappedBy = "agency", fetch = FetchType.EAGER)
    private List<Property> properties;

    @OneToMany(mappedBy = "agency", fetch = FetchType.EAGER)
    private List<User> agents;

    public Agency() {
        this.bookings = new ArrayList<>();
        this.properties = new ArrayList<>();
        this.agents = new ArrayList<>();
    }
}
