package by.academy.springboot.service;

import by.academy.springboot.model.entity.BankAccount;
import by.academy.springboot.model.entity.Currency;
import by.academy.springboot.model.entity.Customer;
import by.academy.springboot.model.entity.PaymentOrder;

import java.util.List;


public interface IBankService {

    BankAccount findBankAccountById(int id);
    List <BankAccount> findAllBankAccountsByCustomerID(Integer customersID);

    List<PaymentOrder> findDistinctByFromAccount_AccountNumberOrToAccount_AccountNumberOrderByTimeStamp(String accountNumber1, String accountNumber2);
    PaymentOrder findPaymentOrderById(int id);
    List<PaymentOrder> findPaymentOrdersByFromAccount_AccountNumberOrderByTimeStamp(String accountNumber);
    List<PaymentOrder> findPaymentOrdersByToAccount_AccountNumberOrderByTimeStamp(String accountNumber);

    Customer findCustomerByID(Integer customerID);

    List<Currency> findAllCurrencies();

    List<Currency> findCurrenciesByCurrencyAbbreviationIsNotOrderByCurrencyAbbreviation(String abbreviation);

    Currency findCurrencyByCurrencyAbbreviationIgnoreCase(String string);
    List<BankAccount> findBankAccountsByCustomerId(int customerId);

    Boolean sendMoneyToBankAccountByAccountsID(Integer toBankAccountID, Double amountInBYN);

}
