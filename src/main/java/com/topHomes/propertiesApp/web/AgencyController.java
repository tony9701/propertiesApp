package com.topHomes.propertiesApp.web;

import com.topHomes.propertiesApp.model.entity.Agency;
import com.topHomes.propertiesApp.model.entity.User;
import com.topHomes.propertiesApp.model.entity.UserRole;
import com.topHomes.propertiesApp.repository.AgencyRepository;
import com.topHomes.propertiesApp.repository.UserRepository;
import com.topHomes.propertiesApp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/agencies")
public class AgencyController {

    private final AgencyRepository agencyRepository;
    private final UserService userService;

    public AgencyController(AgencyRepository agencyRepository, UserService userService) {
        this.agencyRepository = agencyRepository;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String user(@PathVariable Long id, Model model) {
        Optional<Agency> byId = agencyRepository.findById(id);

        if (byId.isPresent()) {
            Agency agency = byId.get();
            List<User> agents = agency.getAgents();
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

    private static boolean isAuthenticated(List<UserRole> roles) {
        return roles.stream().anyMatch(r -> r.getRole().name().equals("ADMIN")) ||
                roles.stream().anyMatch(r -> r.getRole().name().equals("AGENT")) ||
                roles.stream().anyMatch(r -> r.getRole().name().equals("AGENCY_ADMIN"));
    }
}
