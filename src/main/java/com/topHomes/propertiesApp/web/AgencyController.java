package com.topHomes.propertiesApp.web;

import com.topHomes.propertiesApp.model.dto.AddAgentDTO;
import com.topHomes.propertiesApp.model.entity.Agency;
import com.topHomes.propertiesApp.model.entity.User;
import com.topHomes.propertiesApp.model.entity.UserRole;
import com.topHomes.propertiesApp.model.enums.UserRoleEnum;
import com.topHomes.propertiesApp.repository.AgencyRepository;
import com.topHomes.propertiesApp.repository.UserRepository;
import com.topHomes.propertiesApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/agencies")
public class AgencyController {

    private final AgencyRepository agencyRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public AgencyController(AgencyRepository agencyRepository, UserService userService, UserRepository userRepository) {
        this.agencyRepository = agencyRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @GetMapping("/{id}")
    public String agencyPanelByDetailsButton(@PathVariable Long id, Model model) {
        Optional<Agency> byId = agencyRepository.findById(id);

        if (byId.isPresent()) {
            Agency agency = byId.get();
            User user = userService.getCurrentUser().get();

            //check if currentUser is Agent,Admin or Agency-Admin
            if (isAuthenticated(user.getRoles())) {
                model.addAttribute("agency", agency);
                model.addAttribute("user", user);
                return "agency-panel";
            }

            return "not-allowed";
        }

        return "agency-not-found";
    }

    @GetMapping("/agency-panel")
    public String agencyPanel(Model model) {

        //get current user
        User user = userService.getCurrentUser().get();

        //get current agency
        Agency agency = user.getAgency();

        //check if currentUser is Agent,Admin or Agency-Admin
        if (isAuthenticated(user.getRoles())) {
            model.addAttribute("agency", agency);
            model.addAttribute("user", user);
            return "agency-panel";
        }

        return "not-allowed";
    }

    private static boolean isAuthenticated(List<UserRole> roles) {
        return roles.stream().anyMatch(r -> r.getRole().name().equals("ADMIN")) ||
                roles.stream().anyMatch(r -> r.getRole().name().equals("AGENT")) ||
                roles.stream().anyMatch(r -> r.getRole().name().equals("AGENCY_ADMIN"));
    }


    @GetMapping("/add-agent")
    public String addAgentView() {
        return "add-agent";
    }

    @PostMapping("/add-agent")
    public String addAgentDo(@Valid AddAgentDTO addAgentDTO, Model model) {
        //get agent email and check if it registered
        Optional<User> userByEmail = userService.getUserByEmail(addAgentDTO.getEmail());

        //check if user email exists and if that user is not already an agent or agent admin
        if (userByEmail.isEmpty()) {
            return "redirect:/agencies/add-agent";
        } else if (userByEmail.get().getRoles().stream().anyMatch(r -> r.getRole().equals(UserRoleEnum.AGENT))) {
            return "redirect:/agencies/add-agent";
        } else if (userByEmail.get().getRoles().stream().anyMatch(r -> r.getRole().equals(UserRoleEnum.AGENCY_ADMIN))) {
            return "redirect:/agencies/add-agent";
        }

        //get current agency
        User currentUser = userService.getCurrentUser().get();
        Agency agency = currentUser.getAgency();

        //get the user, set AGENCY and  add role "AGENT"
        User user = userByEmail.get();
        user.setAgency(agency);
        userService.addRoleAndSave(user);

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
