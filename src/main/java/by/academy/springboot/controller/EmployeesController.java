package by.academy.springboot.controller;

import by.academy.springboot.model.entity.Employee;
import by.academy.springboot.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class EmployeesController {
    private EmployeeService employeeService;

    @GetMapping(value = "/employees")
    public String getEmployeeList(Model model) {
        List<Employee> all = employeeService.getAll();
        model.addAttribute("employees", all);
        return "employees";
    }

//    @GetMapping(value = "/employee")
//    public String getEmployeeData(){
//        employeeService.
//        return "employee";
//    }
}
