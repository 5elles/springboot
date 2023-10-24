package by.academy.springboot.controller;

import by.academy.springboot.model.entity.Customer;
import by.academy.springboot.model.repository.CustomerRepository;
import by.academy.springboot.service.CustomerService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomersController {
    private final CustomerService customerService;

    @GetMapping
    public String getAllCustomers(Model model){
        model.addAttribute("customers", customerService.getAll());
        return "customers/customers";
    }

    @GetMapping("/{id}")
    public String getOneCustomer(@PathVariable("id") Integer id,
                                 Model model){
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "customers/customer";
    }

    @GetMapping("/new/{id}")
    public String newCustomer(@PathVariable("id") Integer id,
                              @ModelAttribute("customer") Customer customer) {
        customer.setPerson(customerService.getPersonById(id));
        return "customers/newCustomer";

    }

    @PostMapping()
    public String create(@ModelAttribute("customer") Customer customer){
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") Integer id,
                            Model model){
        model.addAttribute("person", customerService.getPersonById(id));
        return "customers/person";
    }


}
