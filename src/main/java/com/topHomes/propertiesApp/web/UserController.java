package com.topHomes.propertiesApp.web;

import com.topHomes.propertiesApp.model.entity.Address;
import com.topHomes.propertiesApp.model.entity.User;
import com.topHomes.propertiesApp.repository.UserRepository;
import com.topHomes.propertiesApp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public String user(@PathVariable Long id, Model model) {
        Optional<User> byId = userRepository.findById(id);

        if (byId.isPresent()) {
            User user = byId.get();
            model.addAttribute("user", user);
            return "user-details";
        }

        return "user-not-found";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, Model model) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            userRepository.delete(user);
            return "redirect:/";
        }

        return "user-not-found";
    }
}
