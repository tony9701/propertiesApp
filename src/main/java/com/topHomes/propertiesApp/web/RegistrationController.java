package com.topHomes.propertiesApp.web;

import com.topHomes.propertiesApp.model.dto.RegisterAgencyDTO;
import com.topHomes.propertiesApp.model.dto.RegisterUserDTO;
import com.topHomes.propertiesApp.service.AgencyService;
import com.topHomes.propertiesApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final AgencyService agencyService;

    public RegistrationController(UserService userService, AgencyService agencyService) {
        this.userService = userService;
        this.agencyService = agencyService;
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

    @ModelAttribute("registerAgencyDTO")
    public RegisterAgencyDTO registerAgencyDTO() {
        return new RegisterAgencyDTO();
    }

    @GetMapping("/agency-register")
    public String agencyRegisterView() {
        return "register-agency";
    }

    @PostMapping("/agency-register")
    public String agencyRegisterDo(@Valid RegisterAgencyDTO registerAgencyDTO,
                                   BindingResult bindingResult) {
        agencyService.registerAgency(registerAgencyDTO);

        return "index";
    }
}
