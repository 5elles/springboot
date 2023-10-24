package by.academy.springboot.controller;

import by.academy.springboot.service.CustomerService;
import by.academy.springboot.service.IBankService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@AllArgsConstructor
//@RequestMapping("")
public class IBankController {
    private final IBankService iBankService;

    @GetMapping("ibank")
    public String getAllBankAccounts(@RequestParam(value = "id") Integer id,
                                     Model model){
        model.addAttribute(
                "customer", iBankService.getCustomerByID(id));
        model.addAttribute(
                "bankAccounts", iBankService.getAllBankAccountsByCustomerID(id));
        model.addAttribute(
                "currencyRates", iBankService.getCurrentCurrencyRates());
        return "ibank/main";
    }
}
