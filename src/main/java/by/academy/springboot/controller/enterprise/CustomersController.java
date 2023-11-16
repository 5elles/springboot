package by.academy.springboot.controller.enterprise;

import by.academy.springboot.dto.*;
import by.academy.springboot.exception.ForbiddenActionException;
import by.academy.springboot.exception.IncorrectParameterException;
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
                                    CustomerDTO dto)
            throws ForbiddenActionException, IncorrectParameterException {
        customerService.createNewBankContract(dto);
        return "redirect:/customer?id=" + cid;
    }

    @PostMapping("/closeAccount")
    public String closeAccount(@RequestParam("aid") int aid,
                               @RequestParam("cid") int cid)
            throws IncorrectParameterException, ForbiddenActionException {
        customerService.closeAccount(aid);
        return "redirect:/customer?id=" + cid;
    }

    @PostMapping("/terminate")
    public String terminateContract(@RequestParam("cid") int cid)
            throws ForbiddenActionException, IncorrectParameterException {
        customerService.terminateContract(cid);
        return "redirect:/customer?id=" + cid;
    }

    @GetMapping("/bankAccount")
    public String showBankAccount(@RequestParam(value = "id") int id,
                                  Model model
    ) throws IncorrectParameterException {
        BankAccountFullDataDTO dto = customerService.findBankAccountFullData(id);
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

    @GetMapping("/findCustomers")
    public String findCustomer() {
        return CUSTOMERS_PATH + "findCustomer";
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
    public String createNewPerson(@ModelAttribute("person") PersonDTO personDTO)
            throws ForbiddenActionException {
        personService.save(personDTO);
        return "redirect:/customers";
    }

    @PostMapping("/newcustomer")
    public String showNewCustomerForm(@RequestParam("pid") int pid,
                                      Model model) {
        model.addAttribute("pid", pid);
        return CUSTOMERS_PATH + "newCustomerForm";
    }

    @PostMapping("/createNewCustomer")
    public String createNewCustomer(@ModelAttribute("customer") CustomerDTO customerDTO)
            throws ForbiddenActionException {
        int id = customerService.createCustomer(customerDTO);
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

    @PostMapping("/createNewAddress")
    public String createNewAddress(@RequestParam("cid") int cid) {
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
                                       @RequestParam("cid") int cid)
            throws IncorrectParameterException, ForbiddenActionException {
        customerService.createNewBankAccount(dto);
        return "redirect:/customer?id=" + cid;
    }

    @PostMapping("/addPhoneNumberCustomer")
    public String addPhoneNumber(@RequestParam("cid") int cid,
                                 Model model) {
        model.addAttribute("pid", customerService.findPersonIdByCustomerId(cid));
        model.addAttribute("entityId", "Customer?cid=" + cid);
        return PERSONS_PATH + "newPhoneNumberForm";
    }

    @PostMapping("/savePhoneNumberCustomer")
    public String savePhoneNumber(@ModelAttribute("phoneNumber") PhoneNumberDTO dto,
                                  @RequestParam("cid") int cid)
            throws ForbiddenActionException, IncorrectParameterException {
        personService.createNewPhoneNumber(dto);
        return "redirect:/customer?id=" + cid;
    }

    @PostMapping("/addEmailCustomer")
    public String addEmail(@RequestParam("cid") int cid,
                           Model model) {
        model.addAttribute("pid", customerService.findPersonIdByCustomerId(cid));
        model.addAttribute("entityId", "Customer?cid=" + cid);
        return PERSONS_PATH + "newEmailForm";
    }

    @PostMapping("/saveEmailCustomer")
    public String saveEmail(@ModelAttribute("email") EmailDTO dto,
                            @RequestParam("cid") int cid) throws ForbiddenActionException, IncorrectParameterException {
        personService.createNewEmail(dto);
        return "redirect:/customer?id=" + cid;
    }
}
