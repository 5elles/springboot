package by.academy.springboot.controller;

import by.academy.springboot.model.entity.Employee;
import by.academy.springboot.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeesController {
    private final EmployeeService employeeService;

    @GetMapping
    public String getEmployeeList(Model model) {
        List<Employee> all = employeeService.getAll();
        model.addAttribute("employees", all);
        return "employees/employees";
    }

    @GetMapping(value = "/{id}")
    public String getEmployee(@PathVariable("id") int id,
                              Model model){
        Employee employee = employeeService.getById(id);
        model.addAttribute("employee", employee);
        return "employees/employee";
    }
}
