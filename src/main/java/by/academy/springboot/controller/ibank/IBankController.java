package by.academy.springboot.controller.ibank;

import by.academy.springboot.model.entity.BankAccount;
import by.academy.springboot.model.entity.Customer;
import by.academy.springboot.model.entity.PaymentOrder;
import by.academy.springboot.service.IBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IBankController {
    private final IBankService iBankService;
    @GetMapping()
    public String showHomePage(){
        return "ibank/startPage";
    }

    @GetMapping("/login")
    public String login(){
        return "ibank/login";
    }

    @GetMapping("/lc")
    public String showAllBankAccounts(@RequestParam(value = "id") int id,
                                     Model model){
        model.addAttribute(
                "customer", iBankService.findCustomerByID(id));
        model.addAttribute(
                "bankAccounts", iBankService.findAllBankAccountsByCustomerID(id));
        model.addAttribute(
                "currencies", iBankService.findCurrenciesByCurrencyAbbreviationIsNotOrderByCurrencyAbbreviation("BYN"));
        return "ibank/main";
    }

    @GetMapping("/account")
    public String showBankAccount(@RequestParam(value = "aid") int aid,
                                  @RequestParam(value = "cid") int cid,
                                  Model model){
        BankAccount account = iBankService.findBankAccountById(aid);
        Customer customer = iBankService.findCustomerByID(cid);
        List<PaymentOrder> allBankOrders = iBankService.findDistinctByFromAccount_AccountNumberOrToAccount_AccountNumberOrderByTimeStamp(account.getAccountNumber(), account.getAccountNumber());
        List<PaymentOrder> fromAccountOrders = iBankService.findPaymentOrdersByFromAccount_AccountNumberOrderByTimeStamp(account.getAccountNumber());
        List<PaymentOrder> toAccountOrders = iBankService.findPaymentOrdersByToAccount_AccountNumberOrderByTimeStamp(account.getAccountNumber());
        model.addAttribute("bankAccount", account);
        model.addAttribute("customer", customer);
        model.addAttribute("allBankOrders", allBankOrders);
        model.addAttribute("fromAccountOrders", fromAccountOrders);
        model.addAttribute("toAccountOrders", toAccountOrders);
        return "ibank/account";
    }

}
