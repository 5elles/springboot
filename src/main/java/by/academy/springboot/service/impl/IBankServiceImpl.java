package by.academy.springboot.service.impl;


import by.academy.springboot.model.entity.BankAccount;
import by.academy.springboot.model.entity.Currency;
import by.academy.springboot.model.entity.Customer;
import by.academy.springboot.model.entity.PaymentOrder;
import by.academy.springboot.model.repository.BankAccountRepository;
import by.academy.springboot.model.repository.CurrencyRepository;
import by.academy.springboot.model.repository.CustomerRepository;
import by.academy.springboot.model.repository.PaymentOrderRepository;
import by.academy.springboot.service.IBankService;
import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class IBankServiceImpl implements IBankService {
    private final BankAccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;
    private final CurrencyRepository currencyRepository;
    private final PaymentOrderRepository paymentOrderRepository;


    @Override
    public BankAccount findBankAccountById(int id) {
        return bankAccountRepository.findById(id).orElse(null);
    }

    @Override
    public List<BankAccount> findAllBankAccountsByCustomerID(Integer customersID) {
        Customer customer = customerRepository.findById(customersID).orElse(null);
        if (customer!=null) {
            return customer.getBankAccounts();
        } return Collections.emptyList();
    }

    @Override
    public List<PaymentOrder> findDistinctByFromAccount_AccountNumberOrToAccount_AccountNumberOrderByTimeStamp(String accountNumber1, String accountNumber2) {
        return paymentOrderRepository.findDistinctByFromAccount_AccountNumberOrToAccount_AccountNumberOrderByTimeStamp(accountNumber1, accountNumber2);
    }

    @Override
    public PaymentOrder findPaymentOrderById(int id) {
        return paymentOrderRepository.findById(id).orElse(null);
    }

    @Override
    public List<PaymentOrder> findPaymentOrdersByFromAccount_AccountNumberOrderByTimeStamp(String accountNumber) {
        return paymentOrderRepository.findPaymentOrdersByFromAccount_AccountNumberOrderByTimeStamp(accountNumber);
    }

    @Override
    public List<PaymentOrder> findPaymentOrdersByToAccount_AccountNumberOrderByTimeStamp(String accountNumber) {
        return paymentOrderRepository.findPaymentOrdersByToAccount_AccountNumberOrderByTimeStamp(accountNumber);
    }


    @Override
    public Customer findCustomerByID(Integer customerID) {
        return customerRepository.findById(customerID).orElse(null);
    }

    @Override
    public List<Currency> findAllCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public List<Currency> findCurrenciesByCurrencyAbbreviationIsNotOrderByCurrencyAbbreviation(String abbreviation) {
        return currencyRepository.findCurrenciesByCurrencyAbbreviationIsNotOrderByCurrencyAbbreviation(abbreviation);
    }

    @Override
    public Currency findCurrencyByCurrencyAbbreviationIgnoreCase(String string) {
        return currencyRepository.findCurrencyByCurrencyAbbreviationIgnoreCase(string);
    }

    @Override
    public List<BankAccount> findBankAccountsByCustomerId(int customerId) {
        return bankAccountRepository.findBankAccountsByCustomerId(customerId);
    }


    @Override
    public Boolean sendMoneyToBankAccountByAccountsID(Integer toBankAccountID, Double amountInBYN) {

        return null;
    }
}
