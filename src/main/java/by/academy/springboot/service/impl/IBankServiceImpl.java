package by.academy.springboot.service.impl;


import by.academy.springboot.model.entity.BankAccount;
import by.academy.springboot.model.entity.Currency;
import by.academy.springboot.model.entity.Customer;
import by.academy.springboot.model.repository.BankAccountRepository;
import by.academy.springboot.model.repository.CurrencyRepository;
import by.academy.springboot.model.repository.CustomerRepository;
import by.academy.springboot.service.IBankService;
import java.util.Collections;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class IBankServiceImpl implements IBankService {
    private final BankAccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;
    private final CurrencyRepository currencyRepository;


    @Override
    public List<BankAccount> getAllBankAccountsByCustomerID(Integer customersID) {
        Customer customer = customerRepository.findById(customersID).orElse(null);
        if (customer!=null) {
            return customer.getBankAccounts();
        } return Collections.emptyList();
    }

    @Override
    public Customer getCustomerByID(Integer customerID) {
        return customerRepository.findById(customerID).orElse(null);
    }

    @Override
    public Map<String, Double> getCurrentCurrencyRates() {
        List<Currency> all = currencyRepository.findAll();
        Map<String, Double> rates = new HashMap<>();
        all.forEach(currency -> rates.put(currency.getCurrencyAbbreviation(), currency.getCurrencyRate()));
        return rates;
    }

    @Override
    public Double getCurrentBalanceByBankAccountID(Integer bankAccountID) {
        Double result = null;
        BankAccount account = bankAccountRepository.findById(bankAccountID).orElse(null);
        if (account != null) result = account.getCurrentBalance();
        return result;
    }




    @Override
    public Boolean sendMoneyToBankAccountByAccountsID(Integer toBankAccountID, Double amountInBYN) {

        return null;
    }
}
