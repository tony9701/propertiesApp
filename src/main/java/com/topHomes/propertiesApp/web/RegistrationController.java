package com.topHomes.propertiesApp.web;

import com.topHomes.propertiesApp.model.dto.RegisterUserDTO;
import com.topHomes.propertiesApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerUserDTO")
    public RegisterUserDTO registerUserDTO() {
        return new RegisterUserDTO();
    }

    @GetMapping("/register")
    public String registerView() {
        return "register-main";
    }

    @GetMapping("/user-register")
    public String userRegisterView() {
        return "register-user";
    }

    @PostMapping("/user-register")
    public String userRegisterDo(@Valid RegisterUserDTO registerUserDTO,
                                 BindingResult bindingResult) {

        userService.registerUser(registerUserDTO);

        return "index";
    }
}
