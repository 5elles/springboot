package by.academy.springboot.controller.enterprise;

import by.academy.springboot.dto.*;
import by.academy.springboot.exception.ForbiddenActionException;
import by.academy.springboot.exception.IncorrectParameterException;
import by.academy.springboot.service.CustomerService;
import by.academy.springboot.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class CustomersController {
    private final CustomerService customerService;
    private final PersonService personService;
    private static final String CUSTOMERS_PATH = "enterprise/customers/";
    private static final String PERSONS_PATH = "enterprise/persons/";
    private static final String CUSTOMER_PAGE = "/customer?id=";
    private static final String CUSTOMER_SUFFIX = "Customer?cid=";
    private static final String REDIRECT = "redirect:";

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping("/customers")
    public String showAllCustomers(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("customers", customerService.findAllCustomers());
        model.addAttribute("personData", personService.findPersonDto(userDetails));

        return CUSTOMERS_PATH + "customers";
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping("/customer")
    public String showOneCustomer(@RequestParam("id") int id,
                                  @AuthenticationPrincipal UserDetails userDetails,
                                  Model model)
            throws IncorrectParameterException {
        CustomerFullDataDTO fullData = customerService.findFullData(id);
        model.addAttribute("customerFullData", fullData);
        model.addAttribute("personData", personService.findPersonDto(userDetails));
        return CUSTOMERS_PATH + "customer";
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @PostMapping("/customer")
    public String createNewContract(@RequestParam("id") int cid,
                                    CustomerDTO dto)
            throws ForbiddenActionException, IncorrectParameterException {
        customerService.createNewBankContract(dto);
        return REDIRECT + CUSTOMER_PAGE + cid;
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping("/closeAccount")
    public String closeAccount(@RequestParam("aid") int aid,
                               @RequestParam("cid") int cid)
            throws IncorrectParameterException, ForbiddenActionException {
        customerService.closeAccount(aid);
        return REDIRECT + CUSTOMER_PAGE + cid;
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping("/terminate")
    public String terminateContract(@RequestParam("cid") int cid)
            throws ForbiddenActionException, IncorrectParameterException {
        customerService.terminateContract(cid);
        return REDIRECT + CUSTOMER_PAGE + cid;
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping("/bankAccount")
    public String showBankAccount(@RequestParam(value = "id") int id,

                                  Model model
    ) throws IncorrectParameterException {
        BankAccountFullDataDTO dto = customerService.findBankAccountFullData(id);
        model.addAttribute("data", dto);
        return CUSTOMERS_PATH + "bankAccount";
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping("/operationsLog")
    public String showBankOperationsLog(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("orders", customerService.findAllPaymentOrders());
        model.addAttribute("personData", personService.findPersonDto(userDetails));
        return CUSTOMERS_PATH + "operationsLog";
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping("/po")
    public String showPaymentOrder(@RequestParam("id") int id,
                                   @AuthenticationPrincipal UserDetails userDetails,
                                   Model model) {
        model.addAttribute("order", customerService.findById(id));
        model.addAttribute("personData", personService.findPersonDto(userDetails));
        return CUSTOMERS_PATH + "paymentOrder";
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
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

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping("/newPersonCustomer")
    public String showNewPersonForm(Model model,
                                    @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("status", "Customer");
        model.addAttribute("personData", personService.findPersonDto(userDetails));
        return PERSONS_PATH + "newPersonForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @PostMapping("/newPersonCustomer")
    public String createNewPerson(@ModelAttribute("person") PersonDTO personDTO)
            throws ForbiddenActionException {
        personService.save(personDTO);
        return REDIRECT + "/customers";
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping("/newcustomer")
    public String showNewCustomerForm(@RequestParam("pid") int pid,
                                      Model model) {
        model.addAttribute("pid", pid);
        return CUSTOMERS_PATH + "newCustomerForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @PostMapping("/createNewCustomer")
    public String createNewCustomer(@ModelAttribute("customer") CustomerDTO customerDTO)
            throws ForbiddenActionException {
        int id = customerService.createCustomer(customerDTO);
        return REDIRECT + CUSTOMER_PAGE + id;
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN', 'ROLE_HR')")
    @GetMapping("/newAddress")
    public String showNewAddressForm(@RequestParam("cid") int cid,
                                     Model model) {
        model.addAttribute("pid", customerService.findPersonIdByCustomerId(cid));
        model.addAttribute("suffix", CUSTOMER_SUFFIX + cid);
        model.addAttribute("data", personService.findAddressesFullData());
        return PERSONS_PATH + "newAddressForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @PostMapping("/createNewAddressCustomer")
    public String createNewAddress(@RequestParam("cid") int cid,
                                   @ModelAttribute("address") NewAddressDTO dto)
            throws ForbiddenActionException {
        personService.createNewAddress(dto);
        return REDIRECT + CUSTOMER_PAGE + cid;
    }


    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping("/newbankaccount")
    public String showNewBankAccountForm(@RequestParam("cid") int cid,
                                         Model model) {
        model.addAttribute("cid", cid);
        model.addAttribute("currencies", customerService.findAllCurrencies());
        return CUSTOMERS_PATH + "newBankAccountForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @PostMapping("/createBankAccount")
    public String createNewBankAccount(@ModelAttribute("bankAccount") BankAccountDTO dto,
                                       @RequestParam("cid") int cid)
            throws IncorrectParameterException, ForbiddenActionException {
        customerService.createNewBankAccount(dto);
        return REDIRECT + CUSTOMER_PAGE + cid;
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping("/addPhoneNumberCustomer")
    public String addPhoneNumber(@RequestParam("cid") int cid,
                                 Model model) {
        model.addAttribute("pid", customerService.findPersonIdByCustomerId(cid));
        model.addAttribute("entityId", CUSTOMER_SUFFIX + cid);
        return PERSONS_PATH + "newPhoneNumberForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @PostMapping("/savePhoneNumberCustomer")
    public String savePhoneNumber(@ModelAttribute("phoneNumber") PhoneNumberDTO dto,
                                  @RequestParam("cid") int cid)
            throws ForbiddenActionException, IncorrectParameterException {
        personService.createNewPhoneNumber(dto);
        return REDIRECT + CUSTOMER_PAGE + cid;
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping("/addEmailCustomer")
    public String addEmail(@RequestParam("cid") int cid,
                           Model model) {
        model.addAttribute("pid", customerService.findPersonIdByCustomerId(cid));
        model.addAttribute("entityId", CUSTOMER_SUFFIX + cid);
        return PERSONS_PATH + "newEmailForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @PostMapping("/saveEmailCustomer")
    public String saveEmail(@ModelAttribute("email") EmailDTO dto,
                            @RequestParam("cid") int cid) throws ForbiddenActionException, IncorrectParameterException {
        personService.createNewEmail(dto);
        return REDIRECT + CUSTOMER_PAGE + cid;
    }
}
