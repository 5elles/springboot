package by.academy.springboot.controller.enterprise;

import by.academy.springboot.dto.BankAccountFullDataDTO;
import by.academy.springboot.dto.CustomerFullDataDTO;
import by.academy.springboot.dto.PersonDTO;
import by.academy.springboot.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CustomersController {
    private final CustomerService customerService;
    private static final String PATH = "/enterprise/customers/";

    @GetMapping("/customers")
    public String showAllCustomers(Model model) {
        model.addAttribute(
                "customers", customerService.findAllCustomers()
        );
        return PATH + "customers";
    }

    @GetMapping("/customer")
    public String showOneCustomer(@RequestParam("id") int id,
                                  Model model) {
        CustomerFullDataDTO fullData = customerService.findFullData(id);
        if (fullData == null) {
            return "notFound";
        }
        model.addAttribute(
                "customerFullData", fullData);
        return PATH + "customer";
    }

    @GetMapping("/bankAccount")
    public String showBankAccount(
            @RequestParam(value = "id") int id,
            Model model
    ) {
        BankAccountFullDataDTO dto = customerService.findBankAccountFullData(id);
        if (dto == null) {
            return "notFound";
        }
        model.addAttribute("data", dto);
        return PATH + "bankAccount";
    }


    @GetMapping("/operationsLog")
    public String showBankOperationsLog(Model model) {
        model.addAttribute("orders", customerService.findAllPaymentOrders());
        return PATH + "operationsLog";
    }

    @GetMapping("/po")
    public String showPaymentOrder(@RequestParam("id") int id,
                                   Model model) {
        model.addAttribute("order", customerService.findById(id));
        return PATH + "paymentOrder";
    }

    @GetMapping("/newperson")
    public String showNewPersonForm(){
        return PATH + "personForm";
    }

    @PostMapping("/newperson")
    public String create(@ModelAttribute("person") PersonDTO personDTO) {
        int id = customerService.save(personDTO);
        if (id > 0) {
            return "redirect:/addressForm?pid=" + id;
        }
        return "operationError";
    }

    @GetMapping("/newAddress")
    public String showNewAddressForm(@RequestParam int id,
                                     Model model){
        model.addAttribute("personId", id);
        model.addAttribute("data", customerService.findFullData());
        return "addressForm";
    }

//    @PostMapping("/newAddress")
//    public String
}
