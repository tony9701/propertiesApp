package com.topHomes.propertiesApp.web;

import com.topHomes.propertiesApp.config.MessageApiConfig;
import com.topHomes.propertiesApp.model.dto.CreateMessageDTO;
import com.topHomes.propertiesApp.model.dto.MessageDTO;
import com.topHomes.propertiesApp.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/admin/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String getAllMessages(Model model) {
        model.addAttribute("allMessages", messageService.getAllMessages());
        return "/admin";
    }

    @GetMapping("/{id}")
    public String getMessage(@PathVariable Long id, Model model) {
        model.addAttribute("message", messageService.getMessageById(id));
        return "/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteMessage(@PathVariable Long id) {
        messageService.deleteMessageById(id);
        return "/admin";
    }
}
