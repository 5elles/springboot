package by.academy.springboot.controller.enterprise;

import by.academy.springboot.dto.*;
import by.academy.springboot.exception.ForbiddenActionException;
import by.academy.springboot.exception.IncorrectParameterException;
import by.academy.springboot.service.EmployeeService;
import by.academy.springboot.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class EmployeesController {
    private final EmployeeService employeeService;
    private final PersonService personService;
    private static final String EMPLOYEES_PATH = "enterprise/employees/";
    private static final String PERSONS_PATH = "enterprise/persons/";
    private static final String ALL_EMPLOYEES = "/employees";
    private static final String EMPLOYEE_PAGE = "/employee?id=";
    private static final String EMPLOYEE_SUFFIX = "Employee?id=";
    private static final String REDIRECT = "redirect:";


    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN')")
    @GetMapping(value = ALL_EMPLOYEES)
    public String showAllEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return EMPLOYEES_PATH + "employees";

    }

    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN')")
    @GetMapping(value = "/employee")
    public String showEmployee(@RequestParam("id") int id,
                               Model model) throws IncorrectParameterException {
        EmployeeFullDataDTO employeeFullData = employeeService.findEmployeeFullData(id);
        model.addAttribute("employee", employeeFullData);
        return EMPLOYEES_PATH + "employee";
    }

    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN')")
    @GetMapping("/findPersons")
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
        return EMPLOYEES_PATH + "personsList";
    }

    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN')")
    @GetMapping("/newPersonEmployee")
    public String showNewPersonEmployeeForm(Model model) {
        model.addAttribute("status", "Employee");
        return PERSONS_PATH + "newPersonForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN')")
    @PostMapping("/newPersonEmployee")
    public String createNewPersonEmployee(@ModelAttribute("person") PersonDTO personDTO) throws ForbiddenActionException {
        personService.save(personDTO);
        return REDIRECT + ALL_EMPLOYEES;
    }

    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN')")
    @GetMapping("/showNewWageRateForm")
    public String setNewWageRate(@RequestParam("pid") int pid,
                                 Model model) {
        model.addAttribute("pid", pid);
        model.addAttribute("data", employeeService.findAllPositions());
        return EMPLOYEES_PATH + "newWageRateForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN')")
    @PostMapping("/setNewWageRate")
    public String setNewWageRate(@ModelAttribute WageRateFullDataDTO dto,
                                 @RequestParam("pid") int pid)
            throws IncorrectParameterException {
        employeeService.createNewWageRate(dto, pid);
        return REDIRECT + EMPLOYEE_PAGE + employeeService.findEmployeeIdByPersonId(pid);
    }

    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN')")
    @GetMapping("/addEmailEmployee")
    public String addEmployeeEmail(@RequestParam("eid") int eid,
                                   Model model) {
        model.addAttribute("pid", employeeService.findPersonIdByEmployeeId(eid));
        model.addAttribute("entityId", "Employee?eid=" + eid);
        return PERSONS_PATH + "newEmailForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN')")
    @PostMapping("/saveEmailEmployee")
    public String addEmployeeEmail(@RequestParam("eid") int eid,
                                   @ModelAttribute("email") EmailDTO dto)
            throws ForbiddenActionException, IncorrectParameterException {
        personService.createNewEmail(dto);
        return REDIRECT + EMPLOYEE_PAGE + eid;
    }

    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN')")
    @GetMapping("/addPhoneNumberEmployee")
    public String addPhoneNumber(@RequestParam("eid") int eid,
                                 Model model) {
        model.addAttribute("pid", employeeService.findPersonIdByEmployeeId(eid));
        model.addAttribute("entityId", "Employee?eid=" + eid);
        return PERSONS_PATH + "newPhoneNumberForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN')")
    @PostMapping("/savePhoneNumberEmployee")
    public String savePhoneNumber(@ModelAttribute("phoneNumber") PhoneNumberDTO dto,
                                  @RequestParam("eid") int eid) throws IncorrectParameterException, ForbiddenActionException {
        personService.createNewPhoneNumber(dto);
        return REDIRECT + EMPLOYEE_PAGE + eid;
    }

    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN')")
    @GetMapping("/fire")
    public String terminateContract(@RequestParam("wid") int wid,
                                    @RequestParam("eid") int eid) throws ForbiddenActionException {
        employeeService.closeWageRate(wid);
        return REDIRECT + EMPLOYEE_PAGE + eid;
    }

    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN')")
    @GetMapping("/staffingTable")
    public String showStaffingTable(Model model) {
        model.addAttribute("data", employeeService.getAllPositions());
        return EMPLOYEES_PATH + "staffingTable";
    }

    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN')")
    @GetMapping("/employeesByPosition")
    public String showEmployeesByPosition(@RequestParam("posId") int posId,
                                          Model model) {
        model.addAttribute("data", employeeService.findAlLActualEmployeesByPositionId(posId));
        model.addAttribute("positionName", employeeService.findPositionNameById(posId));
        return EMPLOYEES_PATH + "employeesByPositon";
    }

    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN')")
    @GetMapping("/findEmployee")
    public String findEm() {
        return EMPLOYEES_PATH + "findEmployee";
    }

    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN')")
    @GetMapping("/addNewAddress")
    public String showNewAddressForm(@RequestParam("eid") int eid,
                                     Model model) {
        model.addAttribute("pid", employeeService.findPersonIdByEmployeeId(eid));
        model.addAttribute("suffix", EMPLOYEE_SUFFIX + eid);
        model.addAttribute("data", personService.findAddressesFullData());
        return PERSONS_PATH + "newAddressForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN')")
    @PostMapping("/createNewAddressEmployee")
    public String createNewAddress(@RequestParam("id") int eid,
                                   @ModelAttribute("address") NewAddressDTO dto)
            throws ForbiddenActionException {
        personService.createNewAddress(dto);
        return REDIRECT + EMPLOYEE_PAGE + eid;
    }
}
