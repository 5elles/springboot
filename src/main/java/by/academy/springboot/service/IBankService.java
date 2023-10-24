package by.academy.springboot.service;

import by.academy.springboot.model.entity.BankAccount;
import by.academy.springboot.model.entity.Currency;
import by.academy.springboot.model.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface IBankService {

    List <BankAccount> getAllBankAccountsByCustomerID(Integer customersID);
    Customer getCustomerByID(Integer customerID);

    Map<String, Double> getCurrentCurrencyRates();
    Double getCurrentBalanceByBankAccountID(Integer bankAccountID);

    Boolean sendMoneyToBankAccountByAccountsID(Integer toBankAccountID, Double amountInBYN);

}
