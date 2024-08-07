package com.topHomes.propertiesApp.web;

import com.topHomes.propertiesApp.model.dto.AddPropertyDTO;
import com.topHomes.propertiesApp.model.entity.Property;
import com.topHomes.propertiesApp.model.enums.UserRoleEnum;
import com.topHomes.propertiesApp.repository.PropertyRepository;
import com.topHomes.propertiesApp.service.PropertyService;
import com.topHomes.propertiesApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;
    private final PropertyRepository propertyRepository;
    private final UserService userService;

    public PropertyController(PropertyService propertyService, PropertyRepository propertyRepository, UserService userService) {
        this.propertyService = propertyService;
        this.propertyRepository = propertyRepository;
        this.userService = userService;
    }

    @ModelAttribute("addPropertyDTO")
    public AddPropertyDTO addPropertyDTO() {
        return new AddPropertyDTO();
    }

    @GetMapping("/{id}")
    public String propertyDetails(@PathVariable Long id, Model model) {
        Optional<Property> property = propertyRepository.findById(id);

        if (property.isPresent()) {
            model.addAttribute("property", property.get());
            return "property-details";
        }

        return "property-not-found";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        Optional<Property> byId = propertyRepository.findById(id);

        if (byId.isPresent()) {
            Property property = byId.get();
            propertyRepository.delete(property);

            if (userService.getCurrentUser().get().getRoles().stream().anyMatch(r -> r.getRole().equals(UserRoleEnum.ADMIN))) {
                return "redirect:/admin";
            }

            return "redirect:/agencies/agency-panel";
        }

        return "property-not-found";
    }

    @GetMapping("/")
    public String propertiesMain() {
        return "properties-main";
    }

    @GetMapping("/buy")
    public String buy(Model model) {
        List<Property> propertiesBuy = propertyService.getPropertiesBuy();
        model.addAttribute("properties", propertiesBuy);

        return "properties-buy-rent";
    }

    @GetMapping("/rent")
    public String rent(Model model) {
        List<Property> propertiesRent = propertyService.getPropertiesRent();
        model.addAttribute("properties", propertiesRent);

        return "properties-buy-rent";
    }

    @GetMapping("/add")
    public String add() {
        return "add-properties";
    }

    @PostMapping("/add")
    public String add(@Valid AddPropertyDTO addPropertyDTO, BindingResult bindingResult) {
        propertyService.addProperty(addPropertyDTO);
        return "redirect:/";
    }

}
