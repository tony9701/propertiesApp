package com.topHomes.propertiesApp.model.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddAgentDTO {

    @NotBlank
    @Email
    private String email;
}
