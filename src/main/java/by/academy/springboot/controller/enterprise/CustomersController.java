package by.academy.springboot.controller.enterprise;

import by.academy.springboot.model.entity.*;
import by.academy.springboot.service.CustomerService;
import by.academy.springboot.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;


@Controller
@AllArgsConstructor
public class CustomersController {
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private static final String PATH = "/enterprise/customers/";



    @GetMapping("/customers")
    public String showAllCustomers(Model model){
        List<Customer> customers = customerService.findAllCustomers();
        customers.sort(Comparator.comparing(o -> o.getPerson().getLastName()));
        model.addAttribute("customers", customers);
        return PATH + "customers";
    }

    @GetMapping("/customer")
    public String showOneCustomer(@RequestParam("id") int id,
                                 Model model){
        Customer customer =  customerService.findCustomerById(id);
        Contact contact = customerService.findContactByPerson(customer.getPerson());
        model.addAttribute("customer", customer);
        model.addAttribute("contact", contact);
        return PATH + "customer";
    }
    @GetMapping("/bankAccount")
    public String showBankAccount(@RequestParam(value = "aid") int aid,
                                  @RequestParam(value = "cid") int cid,
                                  Model model){
        BankAccount account = customerService.findBankAccountById(aid);
        Customer customer = customerService.findCustomerByID(cid);
        List<PaymentOrder> allBankOrders = customerService.findDistinctByFromAccount_AccountNumberOrToAccount_AccountNumberOrderByTimeStamp(account.getAccountNumber(), account.getAccountNumber());
        List<PaymentOrder> fromAccountOrders = customerService.findPaymentOrdersByFromAccount_AccountNumberOrderByTimeStamp(account.getAccountNumber());
        List<PaymentOrder> toAccountOrders = customerService.findPaymentOrdersByToAccount_AccountNumberOrderByTimeStamp(account.getAccountNumber());
        model.addAttribute("bankAccount", account);
        model.addAttribute("customer", customer);
        model.addAttribute("allBankOrders", allBankOrders);
        model.addAttribute("fromAccountOrders", fromAccountOrders);
        model.addAttribute("toAccountOrders", toAccountOrders);
        return PATH + "bankAccount";
    }
    @GetMapping("/new/{id}")
    public String newCustomer(@PathVariable("id") int id,
                              @ModelAttribute("customer") Customer customer) {
        customer.setPerson(customerService.findPersonById(id));
        return PATH + "newCustomer";

    }

    @PostMapping()
    public String create(@ModelAttribute("customer") Customer customer){
        customerService.saveCustomer(customer);
        return "redirect:" + PATH + "customers";
    }

    @GetMapping("/operationsLog")
    public String showBankOperationsLog(Model model){
        model.addAttribute("orders", customerService.findAllPaymentOrdersSortedByDate());
        return PATH + "operationsLog";
    }

    @GetMapping("/po")
    public String showPaymentOrder(@RequestParam("id") int id,
                                   Model model){
        model.addAttribute("order", customerService.findPaymentOrderById(id));
        return PATH + "paymentOrder";
    }
    @GetMapping("/person")
    public String showPerson(@RequestParam("id") int id,
                            Model model){
        Person person = customerService.findPersonById(id);
        if (person.getIsClient() == 1) {
            model.addAttribute("customersID", customerService.findCustomerByPersonId(id).getId());
        }
        if (person.getIsStaff() == 1) {
            model.addAttribute("employeesID", employeeService.findEmployeeByPersonId(id).getId());
        }
        model.addAttribute("person", person);
        return PATH + "person";
    }
}
