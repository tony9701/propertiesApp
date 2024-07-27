package com.topHomes.propertiesApp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAddressDTO {

    @NotBlank
    private String city;
    @NotBlank
    private String country;
    @NotBlank
    private String number;
    @NotBlank
    private String street;

    public CreateAddressDTO(String city, String country, String number, String street) {
        this.city = city;
        this.country = country;
        this.number = number;
        this.street = street;
    }
}
