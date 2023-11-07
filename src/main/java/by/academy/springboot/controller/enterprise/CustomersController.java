package by.academy.springboot.controller.enterprise;

import by.academy.springboot.dto.*;
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

    @PostMapping("/customer")
    public String createNewContract(@RequestParam("cid") int cid,
                                    CustomerDTO dto) {
        if (customerService.createNewBankContract(dto)) {
            return "redirect:/customer?id=" + cid;
        }
        return "notFound";
    }

    @PostMapping("/closeAccount")
    public String closeAccount(@RequestParam("aid") int aid,
                               @RequestParam("cid") int cid) {
        boolean closed = customerService.closeAccount(aid);
        if (closed) {
            return "redirect:/customer?id=" + cid;
        }
        return "operationError";
    }

    @PostMapping("/terminate")
    public String terminateContract(@RequestParam("cid") int cid) {
        if (customerService.terminateContract(cid)) {
            return "redirect:/customer?id=" + cid;
        }
        return "operationError";
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

    @GetMapping("/persons")
    public String showFoundPersons(@RequestParam("lastName") String lastName,
                                   @RequestParam("firstName") String firstName,
                                   @RequestParam("middleName") String middleName,
                                   Model model) {
        model.addAttribute("data",
                customerService.findByLastNameAndFirstNameAndMiddleName(
                        lastName,
                        firstName,
                        middleName
                )
        );
        return PATH + "persons";
    }

    @GetMapping("/newperson")
    public String showNewPersonForm() {
        return PATH + "personForm";
    }

    @PostMapping("/newperson")
    public String createNewPerson(@ModelAttribute("person") PersonDTO personDTO) {
        int id = customerService.save(personDTO);
        if (id > 0) {
            return "redirect:/addressForm?pid=" + id;
        }
        return "operationError";
    }

    @PostMapping("/newcustomer")
    public String showNewCustomerForm(@RequestParam("pid") int pid,
                                      Model model) {
        model.addAttribute("pid", pid);
        return PATH + "newCustomerForm";
    }

    @PostMapping("/createnewcustomer")
    public String createNewCustomer(@ModelAttribute("customer") CustomerDTO customerDTO) {
        int id = customerService.createCustomer(customerDTO);
        if (id < 0) {
            return "operationError";
        }
        return "redirect:/customer?id=" + id;
    }

    @GetMapping("/newaddress")
    public String showNewAddressForm(@RequestParam int id,
                                     Model model) {
        model.addAttribute("personId", id);
        model.addAttribute("data", customerService.findFullData());
        return PATH + "addressForm";
    }

    @PostMapping("/newbankaccount")
    public String showNewBankAccountForm(@RequestParam("cid") int cid,
                                         Model model) {
        model.addAttribute("cid", cid);
        model.addAttribute("currencies", customerService.findAllCurrencies());
        return PATH + "newBankAccountForm";
    }

    @PostMapping("/createBankAccount")
    public String createNewBankAccount(@ModelAttribute("bankAccount") BankAccountDTO dto,
                                       @RequestParam("cid") int cid) {
        if (customerService.createNewBankAccount(dto)) {
            return "redirect:/customer?id=" + cid;
        }
        return "operationError";
    }

    @PostMapping("/addPhoneNumber")
    public String addPhoneNumber(@RequestParam("cid") int cid,
                                 Model model) {
        model.addAttribute("pid", customerService.findPersonIdByCustomerId(cid));
        model.addAttribute("cid", cid);
        return PATH + "newPhoneNumberForm";
    }

    @PostMapping("/savePhoneNumber")
    public String savePhoneNumber(@ModelAttribute("phoneNumber") PhoneNumberDTO dto,
                                  @RequestParam("cid") int cid) {
        if (customerService.createNewPhoneNumber(dto)) {
            return "redirect:/customer?id=" + cid;
        }
        return "operationError";
    }

    @PostMapping("/addEmail")
    public String addEmail(@RequestParam("cid") int cid,
                           Model model) {
        model.addAttribute("pid", customerService.findPersonIdByCustomerId(cid));
        model.addAttribute("cid", cid);
        return PATH + "newEmailForm";
    }

    @PostMapping("/saveEmail")
    public String saveEmail(@ModelAttribute("emal") EmailDTO dto,
                            @RequestParam("cid") int cid) {

        if (customerService.createNewEmail(dto)) {
            return "redirect:/customer?id=" + cid;
        }
        return "operationError";
    }
}
