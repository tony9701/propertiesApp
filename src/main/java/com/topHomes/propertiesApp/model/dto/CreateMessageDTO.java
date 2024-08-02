package com.topHomes.propertiesApp.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateMessageDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    private String senderName;

    @NotBlank
    @Email
    private String senderEmail;

    @NotBlank
    @Size(min = 2, max = 1500)
    private String message;
}
