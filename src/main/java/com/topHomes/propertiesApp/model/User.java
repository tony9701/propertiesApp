package com.topHomes.propertiesApp.model;

import com.topHomes.propertiesApp.model.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "users")
public class User extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    @NotBlank
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

    @ManyToOne(optional = false)
    private Address address;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_favourite_properties",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "property_id")
    )
    private List<Property> favouriteProperties;

    public User() {
        this.bookings = new ArrayList<>();
        this.favouriteProperties = new ArrayList<>();
    }
}
