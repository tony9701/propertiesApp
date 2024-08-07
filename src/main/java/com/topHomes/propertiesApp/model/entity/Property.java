package com.topHomes.propertiesApp.model.entity;

import com.topHomes.propertiesApp.model.entity.BaseEntity.BaseEntity;
import com.topHomes.propertiesApp.model.enums.PropertyTypeEnum;
import com.topHomes.propertiesApp.model.enums.TransactionTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "properties")
public class Property extends BaseEntity {

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "property_type")
    private PropertyTypeEnum propertyTypeEnum;

    @NotBlank
    @Size(max = 1500)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionTypeEnum transactionType;

    @Positive
    @NotNull
    private double price;

    @NotNull
    @Positive
    private double size;

    @OneToMany(mappedBy = "property", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropertyPhoto> propertyPhotos;

    @OneToOne(optional = false)
    private Address address;

    @OneToMany(mappedBy = "property", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;

    @ManyToOne(optional = false)
    private Agency agency;

    @ManyToOne(optional = false)
    private User user;

    @ManyToMany(mappedBy = "favouriteProperties", fetch = FetchType.EAGER)
    private List<User> usersWhoFavorite;

    public Property() {
        this.propertyPhotos = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.usersWhoFavorite = new ArrayList<>();
    }

    public void addPropertyPhoto(PropertyPhoto photo) {
        this.propertyPhotos.add(photo);
    }


    public String getPrice() {
        return String.format("%.0f EUR", price);
    }

    public String getSize() {
        return String.format("%.0f m2", size);
    }
}

