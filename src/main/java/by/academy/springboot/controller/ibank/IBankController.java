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

    @GetMapping()
    public String showHomePage() {
        return "ibank/startPage";
    }

    @GetMapping("/login")
    public String login() {
        return "ibank/login";
    }

    @GetMapping("/lc")
    public String showAllBankAccounts(@RequestParam(value = "id") int id,
                                      Model model) {
        model.addAttribute("customer", iBankService.findFullData(id));
        return "ibank/main";
    }

    @GetMapping("/account")
    public String showBankAccount(@RequestParam(value = "aid") int aid,
                                  Model model) {
        model.addAttribute("data", iBankService.findBankAccountFullData(aid));
        return "ibank/account";
    }

    @PostMapping("/account")
    public String create(@ModelAttribute("order") OrderDTO order,
                         @RequestParam(value = "aid") int aid) {

        boolean isDone = iBankService.save(order);
        if (isDone) {
            return "redirect:/account?aid=" + aid;
        }
        return "operationError";
    }
}
