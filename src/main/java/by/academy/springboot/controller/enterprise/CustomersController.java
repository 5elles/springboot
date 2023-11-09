package by.academy.springboot.controller.enterprise;

import by.academy.springboot.dto.*;
import by.academy.springboot.service.CustomerService;
import by.academy.springboot.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CustomersController {
    private final CustomerService customerService;
    private final PersonService personService;
    private static final String CUSTOMERS_PATH = "/enterprise/customers/";
    private static final String PERSONS_PATH = "/enterprise/persons/";
    private static final String OPERATION_ERROR = "operationError";
    private static final String NOT_FOUND = "notFound";
    private static final String CUSTOMER_PAGE = "/customer?id=";

    @GetMapping("/customers")
    public String showAllCustomers(Model model) {
        model.addAttribute(
                "customers", customerService.findAllCustomers()
        );
        return CUSTOMERS_PATH + "customers";
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
        return CUSTOMERS_PATH + "customer";
    }

    @PostMapping("/customer")
    public String createNewContract(@RequestParam("cid") int cid,
                                    CustomerDTO dto) {
        if (customerService.createNewBankContract(dto)) {
            return "redirect:/customer?id=" + cid;
        }
        return NOT_FOUND;
    }

    @PostMapping("/closeAccount")
    public String closeAccount(@RequestParam("aid") int aid,
                               @RequestParam("cid") int cid) {
        boolean closed = customerService.closeAccount(aid);
        if (closed) {
            return "redirect:/customer?id=" + cid;
        }
        return OPERATION_ERROR;
    }

    @PostMapping("/terminate")
    public String terminateContract(@RequestParam("cid") int cid) {
        if (customerService.terminateContract(cid)) {
            return "redirect:/customer?id=" + cid;
        }
        return OPERATION_ERROR;
    }

    @GetMapping("/bankAccount")
    public String showBankAccount(
            @RequestParam(value = "id") int id,
            Model model
    ) {
        BankAccountFullDataDTO dto = customerService.findBankAccountFullData(id);
        if (dto == null) {
            return NOT_FOUND;
        }
        model.addAttribute("data", dto);
        return CUSTOMERS_PATH + "bankAccount";
    }

    @GetMapping("/operationsLog")
    public String showBankOperationsLog(Model model) {
        model.addAttribute("orders", customerService.findAllPaymentOrders());
        return CUSTOMERS_PATH + "operationsLog";
    }

    @GetMapping("/po")
    public String showPaymentOrder(@RequestParam("id") int id,
                                   Model model) {
        model.addAttribute("order", customerService.findById(id));
        return CUSTOMERS_PATH + "paymentOrder";
    }

    @GetMapping("/persons")
    public String showFoundPersons(@RequestParam("lastName") String lastName,
                                   @RequestParam("firstName") String firstName,
                                   @RequestParam("middleName") String middleName,
                                   Model model) {
        model.addAttribute("data",
                personService.findByLastNameAndFirstNameAndMiddleName(
                        lastName,
                        firstName,
                        middleName
                )
        );
        return CUSTOMERS_PATH + "persons";
    }

    @GetMapping("/newPersonCustomer")
    public String showNewPersonForm(Model model) {
        model.addAttribute("status", "Customer");
        return PERSONS_PATH + "newPersonForm";
    }

    @PostMapping("/newPersonCustomer")
    public String createNewPerson(@ModelAttribute("person") PersonDTO personDTO) {
        int id = personService.save(personDTO);
        if (id > 0) {
            return "redirect:/customers";
        }
        return OPERATION_ERROR;
    }

    @PostMapping("/newcustomer")
    public String showNewCustomerForm(@RequestParam("pid") int pid,
                                      Model model) {
        model.addAttribute("pid", pid);
        return CUSTOMERS_PATH + "newCustomerForm";
    }

    @PostMapping("/createNewCustomer")
    public String createNewCustomer(@ModelAttribute("customer") CustomerDTO customerDTO) {
        int id = customerService.createCustomer(customerDTO);
        if (id < 0) {
            return OPERATION_ERROR;
        }
        return "redirect:" + CUSTOMER_PAGE + id;
    }

    @PostMapping("/newAddress")
    public String showNewAddressForm(@RequestParam("cid") int cid,
                                     Model model) {
        model.addAttribute("pid", customerService.findPersonIdByCustomerId(cid));
        model.addAttribute("cid", cid);
        model.addAttribute("data", personService.findAddressesFullData());
        return PERSONS_PATH + "newAddressForm";
    }

    @PostMapping("createNewAddress")
    public String createNewAddress(@RequestParam("cid") int cid){
        return "redirect:" + CUSTOMER_PAGE + cid;
    }


    @PostMapping("/newbankaccount")
    public String showNewBankAccountForm(@RequestParam("cid") int cid,
                                         Model model) {
        model.addAttribute("cid", cid);
        model.addAttribute("currencies", customerService.findAllCurrencies());
        return CUSTOMERS_PATH + "newBankAccountForm";
    }

    @PostMapping("/createBankAccount")
    public String createNewBankAccount(@ModelAttribute("bankAccount") BankAccountDTO dto,
                                       @RequestParam("cid") int cid) {
        if (customerService.createNewBankAccount(dto)) {
            return "redirect:/customer?id=" + cid;
        }
        return OPERATION_ERROR;
    }

    @PostMapping("/addPhoneNumberCustomer")
    public String addPhoneNumber(@RequestParam("cid") int cid,
                                 Model model) {
        model.addAttribute("pid", customerService.findPersonIdByCustomerId(cid));
        model.addAttribute("entityId", "Customer?cid="+cid);
        return PERSONS_PATH + "newPhoneNumberForm";
    }

    @PostMapping("/savePhoneNumberCustomer")
    public String savePhoneNumber(@ModelAttribute("phoneNumber") PhoneNumberDTO dto,
                                  @RequestParam("cid") int cid) {
        if (personService.createNewPhoneNumber(dto)) {
            return "redirect:/customer?id=" + cid;
        }
        return OPERATION_ERROR;
    }

    @PostMapping("/addEmailCustomer")
    public String addEmail(@RequestParam("cid") int cid,
                           Model model) {
        model.addAttribute("pid", customerService.findPersonIdByCustomerId(cid));
        model.addAttribute("entityId", "Customer?cid="+cid);
        return PERSONS_PATH + "newEmailForm";
    }

    @PostMapping("/saveEmailCustomer")
    public String saveEmail(@ModelAttribute("email") EmailDTO dto,
                            @RequestParam("cid") int cid) {

        if (personService.createNewEmail(dto)) {
            return "redirect:/customer?id=" + cid;
        }
        return OPERATION_ERROR;
    }
}
