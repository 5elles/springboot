package by.academy.springboot.controller.ibank;

import by.academy.springboot.dto.OrderDTO;
import by.academy.springboot.dto.PaymentOrderDTO;
import by.academy.springboot.model.entity.BankAccount;
import by.academy.springboot.model.entity.Customer;
import by.academy.springboot.model.entity.PaymentOrder;
import by.academy.springboot.service.IBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IBankController {
    private final IBankService iBankService;
    private static final String PATH = "/ibank/";
    private static final String OPERATION_ERROR = "operationError";

    @GetMapping()
    public String showHomePage() {
        return PATH + "startPage";
    }

    @GetMapping("/login")
    public String login() {
        return PATH + "login";
    }

    @GetMapping("/lc")
    public String showAllBankAccounts(@RequestParam(value = "id") int id,
                                      Model model) {
        model.addAttribute("customer", iBankService.findFullData(id));
        return PATH + "main";
    }

    @GetMapping("/account")
    public String showBankAccount(@RequestParam(value = "aid") int aid,
                                  Model model) {
        model.addAttribute("data", iBankService.findBankAccountFullData(aid));
        return PATH + "account";
    }

    @PostMapping("/account")
    public String create(@ModelAttribute("order") OrderDTO order,
                         @RequestParam(value = "aid") int aid) {

        boolean isDone = iBankService.save(order);
        if (isDone) {
            return "redirect:/account?aid=" + aid;
        }
        return OPERATION_ERROR;
    }

    @PostMapping("/ibankCloseAccount")
    public String closeBankAccount(@RequestParam("aid") int aid,
                                   @RequestParam("cid") int cid){
        if (iBankService.closeBankAccount(aid)){
            return "redirect:/lc?id=" + cid;
        }
        return OPERATION_ERROR;
    }
}
