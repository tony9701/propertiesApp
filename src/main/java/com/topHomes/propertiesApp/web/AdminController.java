package com.topHomes.propertiesApp.web;

import com.topHomes.propertiesApp.model.dto.MessageDTO;
import com.topHomes.propertiesApp.model.entity.Agency;
import com.topHomes.propertiesApp.model.entity.Property;
import com.topHomes.propertiesApp.model.entity.User;
import com.topHomes.propertiesApp.service.AgencyService;
import com.topHomes.propertiesApp.service.MessageService;
import com.topHomes.propertiesApp.service.PropertyService;
import com.topHomes.propertiesApp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {

    private final UserService userService;
    private final AgencyService agencyService;
    private final PropertyService propertyService;
    private final MessageService messageService;

    public AdminController(UserService userService, AgencyService agencyService, PropertyService propertyService, MessageService messageService) {
        this.userService = userService;
        this.agencyService = agencyService;
        this.propertyService = propertyService;
        this.messageService = messageService;
    }


    @GetMapping("/admin")
    public String admin(Model model) {
        List<User> allUsers = userService.getAllUsers();
        List<Agency> allAgencies = agencyService.getAllAgencies();
        List<Property> allProperties = propertyService.getAllProperties();
        List<MessageDTO> allMessages = messageService.getAllMessages();

        model.addAttribute("allUsers", allUsers);
        model.addAttribute("allAgencies", allAgencies);
        model.addAttribute("allProperties", allProperties);
        model.addAttribute("allMessages", allMessages);
        return "admin-panel";
    }
}
