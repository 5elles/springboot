package by.academy.springboot.controller.ibank;

import by.academy.springboot.dto.OrderDTO;
import by.academy.springboot.exception.ForbiddenActionException;
import by.academy.springboot.exception.IncorrectParameterException;
import by.academy.springboot.service.IBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class IBankController {
    private final IBankService iBankService;
    private static final String PATH = "ibank/";
    private static final String REDIRECT = "redirect:";
    private static final String HOME_URL = "/home";

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @GetMapping()
    public String showHomePage() {
        return REDIRECT + HOME_URL;
    }


    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @GetMapping("/lc")
    public String showAllBankAccounts(@RequestParam(value = "id") int id,
                                      @AuthenticationPrincipal UserDetails userDetails,
                                      Model model)
    throws ForbiddenActionException {
        if (iBankService.isAllowedToHaveAccess(userDetails, id)){
            model.addAttribute("data", iBankService.findFullData(id));
        }
        return PATH + "main";
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @GetMapping("/account")
    public String showBankAccount(@RequestParam(value = "aid") int aid,
                                  @AuthenticationPrincipal UserDetails userDetails,
                                  Model model) throws ForbiddenActionException {
        if (iBankService.isAllowedToAccountAccess(userDetails, aid)){
            model.addAttribute("data", iBankService.findBankAccountFullData(aid));
        }
        return PATH + "account";
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @PostMapping("/account")
    public String create(@ModelAttribute("order") OrderDTO order,
                         @RequestParam(value = "aid") int aid) throws ForbiddenActionException, IncorrectParameterException {
        iBankService.save(order);
        return REDIRECT + "/account?aid=" + aid;
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @GetMapping("/ibankCloseAccount")
    public String closeBankAccount(@RequestParam("aid") int aid,
                                   @RequestParam("cid") int cid) throws ForbiddenActionException, IncorrectParameterException {
        iBankService.closeBankAccount(aid);
        return REDIRECT + "/lc?id=" + cid;
    }
}
