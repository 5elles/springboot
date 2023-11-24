package by.academy.springboot.controller;

import by.academy.springboot.dto.UserRegistrationDTO;
import by.academy.springboot.exception.ForbiddenActionException;
import by.academy.springboot.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private static final String REDIRECT = "redirect:";

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute("user") UserRegistrationDTO dto)
            throws ForbiddenActionException {
        registrationService.registerNewUser(dto);
        return REDIRECT + "/home";
    }
}
