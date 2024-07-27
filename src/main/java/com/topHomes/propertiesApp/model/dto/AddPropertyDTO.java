package com.topHomes.propertiesApp.model.dto;

import com.topHomes.propertiesApp.model.enums.PropertyTypeEnum;
import com.topHomes.propertiesApp.model.enums.TransactionTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddPropertyDTO {

    @NotBlank
    private String photoUrl;

    @NotBlank
    @Size(min = 3, max = 50)
    private String propertyName;

    @NotNull
    private TransactionTypeEnum transactionType;

    @NotNull
    @Positive
    private double price;

    @NotNull
    @Positive
    private double size;

    @NotNull
    private PropertyTypeEnum propertyType;

    @NotBlank
    @Size(min = 3, max = 50)
    private String country;

    @NotBlank
    @Size(min = 3, max = 50)
    private String city;

    @NotBlank
    @Size(min = 3, max = 50)
    private String street;

    @NotBlank
    @Size(min = 3, max = 50)
    private String number;

    @NotBlank
    @Size(min = 5, max = 1500)
    private String description;
}
