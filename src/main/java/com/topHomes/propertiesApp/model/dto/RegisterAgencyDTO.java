package com.topHomes.propertiesApp.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterAgencyDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    private String agencyName;

    @NotBlank
    @Email
    private String email;

    @Size(min = 10, max = 13)
    private String mobileNumber;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotBlank
    @Size(min = 6)
    private String confirmPassword;
}
