package by.academy.springboot.controller;

import by.academy.springboot.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
//    private final RegistrationService registrationService;
//
//    @GetMapping("/registration")
//    public String registration() {
//        return "registration";
//    }
//
//    @PostMapping("/registration")
//    public String addUser(String citizenId, String login, String password) {
//       registrationService.registerNewSystemUser(login, password, citizenId);
//        return "redirect:/login";
//    }
}
