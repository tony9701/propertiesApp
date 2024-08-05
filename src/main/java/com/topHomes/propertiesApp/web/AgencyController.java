package com.topHomes.propertiesApp.web;

import com.topHomes.propertiesApp.model.dto.AddAgentDTO;
import com.topHomes.propertiesApp.model.entity.Agency;
import com.topHomes.propertiesApp.model.entity.User;
import com.topHomes.propertiesApp.model.enums.UserRoleEnum;
import com.topHomes.propertiesApp.repository.UserRepository;
import com.topHomes.propertiesApp.service.AgencyService;
import com.topHomes.propertiesApp.service.UserService;
import com.topHomes.propertiesApp.service.exception.ObjectNotFoundException;
import com.topHomes.propertiesApp.service.exception.UserAlreadyExistsException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/agencies")
public class AgencyController {


    private final AgencyService agencyService;
    private final UserService userService;
    private final UserRepository userRepository;

    public AgencyController(AgencyService agencyService, UserService userService, UserRepository userRepository) {
        this.agencyService = agencyService;
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @GetMapping("/{id}")
    public String agencyPanelByDetailsButton(@PathVariable Long id, Model model) {
        Agency agency = agencyService.getAgencyById(id);
        User user = userService.getCurrentUser().get();
        boolean isUserAuthenticated = userService.isUserAuthenticated();

        if (isUserAuthenticated) {
            model.addAttribute("agency", agency);
            model.addAttribute("user", user);
            return "agency-panel";
        }

        return "not-allowed";
    }

    @GetMapping("/agency-panel")
    public String agencyPanel(Model model) {

        //get current user
        User user = userService.getCurrentUser().get();
        //get current agency
        Agency agency = user.getAgency();
        boolean isAuthenticated = userService.isUserAuthenticated();

        if (isAuthenticated) {
            model.addAttribute("agency", agency);
            model.addAttribute("user", user);
            return "agency-panel";
        }

        return "not-allowed";
    }

    @GetMapping("/add-agent")
    public String addAgentView() {
        return "add-agent";
    }

    @PostMapping("/add-agent")
    public String addAgentDo(@Valid AddAgentDTO addAgentDTO, RedirectAttributes redirectAttributes) {

        try {
            agencyService.addAgent(addAgentDTO);
        } catch (ObjectNotFoundException | UserAlreadyExistsException e) {
            redirectAttributes.addFlashAttribute("isProblem", true);
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/agencies/add-agent";
        }

        return "redirect:/agencies/agency-panel";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAgent(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            return "redirect:/agencies/agency-panel";
        }

        User user = userOptional.get();

        if (user.getRoles().stream().anyMatch(r -> r.getRole().equals(UserRoleEnum.AGENT))) {
            userService.deleteAgent(user);
        }

        if (userService.getCurrentUser().get().getRoles().stream().anyMatch(r -> r.getRole().equals(UserRoleEnum.ADMIN))) {
            return "redirect:/admin";
        }

        return "redirect:/agencies/agency-panel";
    }

    @GetMapping("/agents/{id}")
    public String agentDetails(@PathVariable Long id, Model model) {
        Optional<User> byId = userRepository.findById(id);

        if (byId.isPresent()) {
            User user = byId.get();

            if (user.getAgency() == null) {
                return "user-not-found";
            }

            if (!userService.getCurrentUser().get().getRoles().stream().anyMatch(r -> r.getRole().equals(UserRoleEnum.ADMIN))) {
                if (user.getAgency() != userService.getCurrentUser().get().getAgency()) {
                    return "redirect:/agencies/agency-panel";
                }
            }

            model.addAttribute("user", user);
            return "user-details";
        }

        return "user-not-found";
    }
}
