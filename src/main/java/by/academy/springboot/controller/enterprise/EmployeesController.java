package by.academy.springboot.controller.enterprise;

import by.academy.springboot.dto.EmployeeFullDataDTO;
import by.academy.springboot.dto.EmployeeDTO;
import by.academy.springboot.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeesController {
    private final EmployeeService employeeService;
    private static final String PATH = "/enterprise/employees/";

    @GetMapping(value = "/employees")
    public String showAll(Model model) {
        List<EmployeeDTO> employeeDTOList = employeeService.findAll();
        if (employeeDTOList.isEmpty()){
            return "notFound";
        } else {
            model.addAttribute("employees", employeeDTOList);
            return PATH + "employees";
        }
    }

    @GetMapping(value = "/employee")
    public String showEmployee(@RequestParam("id") int id,
                              Model model){
        EmployeeFullDataDTO employeeWithContacts = employeeService.getEmployeeWithContacts(id);
        if (employeeWithContacts == null){
            return "notFound";
        } else {
            model.addAttribute("employee", employeeWithContacts);
            return PATH + "employee";
        }
    }
}
