package by.academy.springboot.controller.enterprise;

import by.academy.springboot.dto.*;
import by.academy.springboot.service.EmployeeService;
import by.academy.springboot.service.PersonService;
import lombok.RequiredArgsConstructor;
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
    private static final String EMPLOYEES_PATH = "/enterprise/employees/";
    private static final String PERSONS_PATH = "/enterprise/persons/";
    private static final String NOT_FOUND = "notFound";
    private static final String OPERATION_ERROR = "operationError";
    private static final String ALL_EMPLOYEES = "/employees";
    private static final String EMPLOYEE = "/employee";


    @GetMapping(value = ALL_EMPLOYEES)
    public String showAllEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return EMPLOYEES_PATH + "employees";

    }

    @GetMapping(value = EMPLOYEE)
    public String showEmployee(@RequestParam("id") int id,
                               Model model) {
        EmployeeFullDataDTO employeeFullData = employeeService.findEmployeeFullData(id);
        if (employeeFullData == null) {
            return NOT_FOUND;
        } else {
            model.addAttribute("employee", employeeFullData);
            return EMPLOYEES_PATH + "employee";
        }
    }

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

    @GetMapping("/newPersonEmployee")
    public String showNewPersonEmployeeForm(Model model) {
        model.addAttribute("status", "Employee");
        return PERSONS_PATH + "newPersonForm";
    }

    @PostMapping("/newPersonEmployee")
    public String createNewPersonEmployee(@ModelAttribute("person") PersonDTO personDTO) {
        int id = personService.save(personDTO);
        if (id > 0) {
            return "redirect:" + ALL_EMPLOYEES;
        }
        return OPERATION_ERROR;
    }

    @PostMapping("/showNewWageRateForm")
    public String setNewWageRate(@RequestParam("pid") int pid,
                                 Model model){
        model.addAttribute("pid", pid);
        model.addAttribute("data", employeeService.findAllPositions());
        return EMPLOYEES_PATH + "newWageRateForm";
    }

    @PostMapping("/setNewWageRate")
    public String setNewWageRate(@ModelAttribute WageRateFullDataDTO dto,
                                 @RequestParam("pid") int pid){
        if (employeeService.createNewWageRate(dto, pid)){
            return "redirect:" + EMPLOYEE + "?id=" + employeeService.findEmployeeIdByPersonId(pid);
        }
        return OPERATION_ERROR;
    }

    @PostMapping("/addEmailEmployee")
    public String addEmployeeEmail(@RequestParam("eid") int eid,
                           Model model) {
        model.addAttribute("pid", employeeService.findPersonIdByEmployeeId(eid));
        model.addAttribute("entityId", "Employee?eid=" + eid);
        return PERSONS_PATH + "newEmailForm";
    }

    @PostMapping("/saveEmailEmployee")
    public String addEmployeeEmail(@RequestParam("eid") int eid,
                                   @ModelAttribute("email") EmailDTO dto) {
        if (personService.createNewEmail(dto)) {
            return "redirect:" + EMPLOYEE + "?id=" + eid;
        }
        return OPERATION_ERROR;
    }

    @PostMapping("/addPhoneNumberEmployee")
    public String addPhoneNumber(@RequestParam("eid") int eid,
                                 Model model) {
        model.addAttribute("pid", employeeService.findPersonIdByEmployeeId(eid));
        model.addAttribute("entityId", "Employee?eid=" + eid);
        return PERSONS_PATH + "newPhoneNumberForm";
    }

    @PostMapping("/savePhoneNumberEmployee")
    public String savePhoneNumber(@ModelAttribute("phoneNumber") PhoneNumberDTO dto,
                                  @RequestParam("eid") int eid) {
        if (personService.createNewPhoneNumber(dto)) {
            return "redirect:" + EMPLOYEE + "?id=" + eid;
        }
        return OPERATION_ERROR;
    }

    @PostMapping("/fire")
    public String terminateContract(@RequestParam("wid") int wid,
                                    @RequestParam("eid") int eid) {
        if (employeeService.closeWageRate(wid)) {
            return "redirect:" + EMPLOYEE + "?id=" + eid;
        }
        return OPERATION_ERROR;
    }

    @GetMapping("/staffingTable")
    public String showStaffingTable(Model model){
        model.addAttribute("data", employeeService.getAllPositions());
        return EMPLOYEES_PATH + "staffingTable";
    }

    @GetMapping("/employeesByPosition")
    public String showEmployeesByPosition(@RequestParam("posId") int posId,
                                          Model model){
        model.addAttribute("data", employeeService.findAlLActualEmployeesByPositionId(posId));
        model.addAttribute("positionName", employeeService.findPositionNameById(posId));
        return EMPLOYEES_PATH + "employeesByPositon";
    }

}
