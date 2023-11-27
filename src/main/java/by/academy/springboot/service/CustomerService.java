package by.academy.springboot.service;

import by.academy.springboot.dto.*;
import by.academy.springboot.exception.IncorrectParameterException;
import by.academy.springboot.model.entity.Customer;
import by.academy.springboot.model.entity.Person;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> findAllCustomers();
    CustomerFullDataDTO findFullData(Integer customerId);
    List<PaymentOrderDTO> findAllOutgoingAndIncoming(String outgoingAccountNumber, String incomingAccountNumber);
    PaymentOrderDTO findById(int id);
    List<PaymentOrderDTO> findByFromAccountNumber(String accountNumber);
    List<PaymentOrderDTO> findByToAccountNumber(String accountNumber);
    BankAccountFullDataDTO findBankAccountFullData(int bankAccountId) throws IncorrectParameterException;
    List<PaymentOrderDTO> findAllPaymentOrders();
    void closeAccount(int accountId);
    void terminateContract(int customerId);
    boolean hasActiveBankAccounts(int customerID);
    void createNewBankContract(CustomerDTO dto);
    boolean isForbiddenForCreation(Customer customer);
    List<CurrencyDTO> findAllCurrencies();
    void createNewBankAccount(BankAccountDTO dto);
    boolean isReadyForBankAccountCreation(Customer customer, BankAccountDTO dto);
    boolean isForbiddenForCreation(Person person, Customer customer);
    Integer createCustomer(CustomerDTO dto);
    Integer findPersonIdByCustomerId(Integer customerId);
    void setCustomerRole(Customer customer);
}
