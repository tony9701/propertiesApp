package com.topHomes.propertiesApp.web;

import com.topHomes.propertiesApp.model.dto.RegisterAgencyDTO;
import com.topHomes.propertiesApp.model.dto.RegisterUserDTO;
import com.topHomes.propertiesApp.service.AgencyService;
import com.topHomes.propertiesApp.service.UserService;
import com.topHomes.propertiesApp.service.exception.UserAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RegistrationControllerTest {    // Successfully registers a new user and redirects to login page

    @Mock
    private UserService userService;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private RegistrationController registrationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_successful_user_registration() {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO("test@example.com", "John", "Doe", "password123");
        String result = registrationController.userRegisterDo(registerUserDTO, redirectAttributes);
        verify(userService, times(1)).registerUser(registerUserDTO);
        assertEquals("redirect:/login", result);
    }

    // Redirects to user registration page if user already exists
    @Test
    public void test_redirect_to_user_registration_page_if_user_already_exists() {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO("test@example.com", "John", "Doe", "password123");
        doThrow(new UserAlreadyExistsException("User already exists")).when(userService).registerUser(registerUserDTO);

        String result = registrationController.userRegisterDo(registerUserDTO, redirectAttributes);

        verify(userService, times(1)).registerUser(registerUserDTO);
        verify(redirectAttributes, times(1)).addFlashAttribute("registerUserDTO", registerUserDTO);
        verify(redirectAttributes, times(1)).addFlashAttribute("isProblem", true);
        assertEquals("redirect:/user-register", result);
    }

    // Successfully registers a new agency and redirects to login page
    @Test
    public void test_successful_agency_registration() {
        AgencyService agencyService = Mockito.mock(AgencyService.class);
        RegistrationController registrationController = new RegistrationController(null, agencyService);
        RegisterAgencyDTO registerAgencyDTO = new RegisterAgencyDTO();
        registerAgencyDTO.setAgencyName("Test Agency");
        registerAgencyDTO.setEmail("test@agency.com");
        registerAgencyDTO.setMobileNumber("1234567890");
        registerAgencyDTO.setPassword("password");
        registerAgencyDTO.setConfirmPassword("password");
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        String result = registrationController.agencyRegisterDo(registerAgencyDTO, redirectAttributes);

        assertEquals("redirect:/login", result);
        Mockito.verify(agencyService).registerAgency(registerAgencyDTO);
    }

    // Redirects to agency registration page if agency already exists
    @Test
    public void test_redirect_to_agency_registration_page_if_already_exists() {
        AgencyService agencyService = Mockito.mock(AgencyService.class);
        RegistrationController registrationController = new RegistrationController(null, agencyService);
        RegisterAgencyDTO registerAgencyDTO = new RegisterAgencyDTO();
        registerAgencyDTO.setAgencyName("Test Agency");
        registerAgencyDTO.setEmail("test@agency.com");
        registerAgencyDTO.setMobileNumber("1234567890");
        registerAgencyDTO.setPassword("password");
        registerAgencyDTO.setConfirmPassword("password");
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        doThrow(new UserAlreadyExistsException("User already exists")).when(agencyService).registerAgency(registerAgencyDTO);


        String result = registrationController.agencyRegisterDo(registerAgencyDTO, redirectAttributes);

        assertEquals("redirect:/agency-register", result);
        Mockito.verify(agencyService).registerAgency(registerAgencyDTO);
    }
}
