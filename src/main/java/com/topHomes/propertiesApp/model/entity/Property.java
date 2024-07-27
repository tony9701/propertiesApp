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

    @OneToMany(mappedBy = "property", fetch = FetchType.EAGER)
    private List<PropertyPhoto> propertyPhotos;

    @OneToOne(optional = false)
    private Address address;

    @OneToMany(mappedBy = "property", fetch = FetchType.EAGER)
    private List<Booking> bookings;

    @ManyToOne(optional = false)
    private Agency agency;

    @ManyToMany(mappedBy = "favouriteProperties", fetch = FetchType.EAGER)
    private List<User> usersWhoFavorite;

    public Property() {
        this.propertyPhotos = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.usersWhoFavorite = new ArrayList<>();
    }
}

