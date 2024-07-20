package com.topHomes.propertiesApp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/properties")
public class PropertiesController {


    @GetMapping("/buy")
    public String buy() {
        return "properties-buy";
    }
}
