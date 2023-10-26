package by.academy.springboot.controller.enterprise;

import by.academy.springboot.model.entity.Contact;
import by.academy.springboot.model.entity.Employee;
import by.academy.springboot.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class EmployeesController {
    private final EmployeeService employeeService;
    private static final String PATH = "/enterprise/employees/";

    @GetMapping(value = "/employees")
    public String showAll(Model model) {
        model.addAttribute("employees", employeeService.findAllSortedByLastName());
        return PATH + "employees";
    }

    @GetMapping(value = "/employee")
    public String showEmployee(@RequestParam("id") int id,
                              Model model){
        Employee employee = employeeService.findById(id);
        Contact contact = employeeService.findContactByPerson(employee.getPerson());
        model.addAttribute("employee", employee);
        model.addAttribute("contact", contact);
        return PATH + "employee";
    }
}
