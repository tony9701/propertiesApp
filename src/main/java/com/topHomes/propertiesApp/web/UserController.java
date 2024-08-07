package com.topHomes.propertiesApp.web;

import com.topHomes.propertiesApp.model.entity.Address;
import com.topHomes.propertiesApp.model.entity.User;
import com.topHomes.propertiesApp.model.enums.UserRoleEnum;
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
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String user(@PathVariable Long id, Model model) {
        Optional<User> byId = userRepository.findById(id);

        if (byId.isPresent()) {
            User user = byId.get();
            String roles = user.getRoles().stream()
                    .map(role -> role.getRole().name())
                    .collect(Collectors.joining(", "));
            model.addAttribute("user", user);
            model.addAttribute("roles", roles);
            return "user-details";
        }

        return "user-not-found";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, Model model) {
        //TODO Move in the service
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();

            if (user.getRoles().stream().anyMatch(role -> role.getRole().equals((UserRoleEnum.ADMIN)))) {
                return "not-allowed";
            }

            userRepository.delete(user);
            return "redirect:/admin";
        }

        return "user-not-found";
    }

    @GetMapping("/my-properties")
    public String myProperties(Model model) {

        User user = userService.getCurrentUser().get();

        model.addAttribute("user", user);
        return "my-properties";
    }
}
