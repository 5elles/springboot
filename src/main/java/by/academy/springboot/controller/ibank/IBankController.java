package by.academy.springboot.controller.ibank;

import by.academy.springboot.dto.OrderDTO;
import by.academy.springboot.exception.ForbiddenActionException;
import by.academy.springboot.exception.IncorrectParameterException;
import by.academy.springboot.service.IBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class IBankController {
    private final IBankService iBankService;
    private static final String PATH = "ibank/";
    private static final String REDIRECT = "redirect:";

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping()
    public String showHomePage() {
        return PATH + "startPage";
    }

    @GetMapping("/login")
    public String login() {
        return PATH + "login";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/lc")
    public String showAllBankAccounts(@RequestParam(value = "id") int id,
                                      Model model) {
        model.addAttribute("data", iBankService.findFullData(id));
        return PATH + "main";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/account")
    public String showBankAccount(@RequestParam(value = "aid") int aid,
                                  Model model) {
        model.addAttribute("data", iBankService.findBankAccountFullData(aid));
        return PATH + "account";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @PostMapping("/account")
    public String create(@ModelAttribute("order") OrderDTO order,
                         @RequestParam(value = "aid") int aid) throws ForbiddenActionException, IncorrectParameterException {
        iBankService.save(order);
        return REDIRECT + "/account?aid=" + aid;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/ibankCloseAccount")
    public String closeBankAccount(@RequestParam("aid") int aid,
                                   @RequestParam("cid") int cid) throws ForbiddenActionException, IncorrectParameterException {
        iBankService.closeBankAccount(aid);
        return REDIRECT + "/lc?id=" + cid;
    }
}
