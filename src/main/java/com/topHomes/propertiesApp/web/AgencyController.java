package com.topHomes.propertiesApp.web;

import com.topHomes.propertiesApp.model.entity.Agency;
import com.topHomes.propertiesApp.model.entity.User;
import com.topHomes.propertiesApp.repository.AgencyRepository;
import com.topHomes.propertiesApp.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/agencies")
public class AgencyController {

    private final AgencyRepository agencyRepository;

    public AgencyController(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    @GetMapping("/{id}")
    public String user(@PathVariable Long id, Model model) {
        Optional<Agency> byId = agencyRepository.findById(id);

        if (byId.isPresent()) {
            Agency agency = byId.get();
            model.addAttribute("agency", agency);
            return "agency-details";
        }

        return "agency-not-found";
    }
}
