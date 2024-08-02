package com.topHomes.propertiesApp.web;

import com.topHomes.propertiesApp.model.dto.ContactUsDTO;
import com.topHomes.propertiesApp.model.dto.CreateMessageDTO;
import com.topHomes.propertiesApp.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactUsController {

    private final MessageService messageService;

    public ContactUsController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/contact-us")
    public String contactUsView() {
        return "contact-us";
    }

    @PostMapping("/contact-us")
    public String createMessage(@Valid CreateMessageDTO createMessageDTO) {
        messageService.createMessage(createMessageDTO);
        return "redirect:/contact-us";
    }
}
