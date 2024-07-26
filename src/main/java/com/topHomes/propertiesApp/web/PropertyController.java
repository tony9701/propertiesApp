package com.topHomes.propertiesApp.web;

import com.topHomes.propertiesApp.model.dto.AddPropertyDTO;
import com.topHomes.propertiesApp.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @ModelAttribute("addPropertyDTO")
    public AddPropertyDTO addPropertyDTO() {
        return new AddPropertyDTO();
    }


    @GetMapping("/buy")
    public String buy() {
        return "properties-buy";
    }

    @GetMapping("/add")
    public String add() {
        return "add-properties";
    }

    @PostMapping("/add")
    public String add(@Valid AddPropertyDTO addPropertyDTO, BindingResult bindingResult) {
        propertyService.addProperty(addPropertyDTO);
        return "/";
    }

}
