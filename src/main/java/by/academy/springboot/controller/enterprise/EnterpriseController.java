package by.academy.springboot.controller.enterprise;

import by.academy.springboot.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class EnterpriseController {
    private final EmployeeService employeeService;
    private static final String PATH = "/enterprise/";
    @GetMapping("/auth")
    public String showLoginForm(){
        return PATH + "enterpriseLogin";
    }

    @GetMapping("/home")
    public String showHomePage(@RequestParam("id") int id,
                               Model model){
        model.addAttribute(employeeService.findById(id));
        return PATH + "home";
    }
}
