package com.topHomes.propertiesApp.web;

import com.topHomes.propertiesApp.model.dto.ContactUsDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactUsController {

    @GetMapping("/contact-us")
    public String contactUsView() {
        return "contact-us";
    }

    @PostMapping("/contact-us")
    public String contactUsDo(ContactUsDTO contactUsDTO) {

        //TODO REST API!

        return "redirect:/";
    }
}
