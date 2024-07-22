package com.topHomes.propertiesApp.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class RegisterUserDTO {
        @NotEmpty
        @Size(min = 2, max = 20)
        private String firstName;

        @NotEmpty
        @Size(min = 2, max = 20)
        private String lastName;

        @NotEmpty
        @Email
        private String email;

        @NotEmpty
        private String password;

        @NotEmpty
        private String confirmPassword;

        public RegisterUserDTO(String email, String firstName, String lastName, String password) {
                this.email = email;
                this.firstName = firstName;
                this.lastName = lastName;
                this.password = password;
        }

        public RegisterUserDTO() {
        }
}
