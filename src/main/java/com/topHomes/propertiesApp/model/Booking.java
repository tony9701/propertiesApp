package com.topHomes.propertiesApp.model;

import com.topHomes.propertiesApp.model.BaseEntity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "bookings")
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
