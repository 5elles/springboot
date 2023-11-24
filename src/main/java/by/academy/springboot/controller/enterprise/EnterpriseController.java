package by.academy.springboot.controller.enterprise;

import by.academy.springboot.service.EmployeeService;
import by.academy.springboot.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class EnterpriseController {
    private final PersonService personService;
    private static final String PATH = "enterprise/";

    @GetMapping("/login")
    public String showLoginForm(){
        return "enterpriseLogin";
    }


    @GetMapping("/home")
    public String showHomePage(@AuthenticationPrincipal UserDetails userDetails,
                               Model model)
    {
        model.addAttribute("personData", personService.findPersonDto(userDetails));
        return PATH + "home";
    }
}
