package com.topHomes.propertiesApp.model.entity;

import com.topHomes.propertiesApp.model.entity.BaseEntity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking extends BaseEntity {

    @Column(nullable = false, name = "booking_date")
    private LocalDate bookingDate;

    @ManyToOne(optional = false)
    private Property property;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Agency agency;
}
