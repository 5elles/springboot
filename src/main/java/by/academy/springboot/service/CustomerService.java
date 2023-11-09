package by.academy.springboot.service;

import by.academy.springboot.dto.*;
import by.academy.springboot.model.entity.Person;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> findAllCustomers();

    CustomerFullDataDTO findFullData(Integer customerId);

    List<PaymentOrderDTO> findAllOutgoingAndIncoming(String outgoingAccountNumber, String incomingAccountNumber);

    PaymentOrderDTO findById(int id);

    List<PaymentOrderDTO> findByFromAccountNumber(String accountNumber);

    List<PaymentOrderDTO> findByToAccountNumber(String accountNumber);

    BankAccountFullDataDTO findBankAccountFullData(int bankAccountId);

    List<PaymentOrderDTO> findAllPaymentOrders();

    boolean closeAccount(int accountId);
    boolean terminateContract(int customerId);
    boolean hasActiveBankAccounts(int customerID);
    boolean createNewBankContract(CustomerDTO dto);
    List<CurrencyDTO> findAllCurrencies();
    boolean createNewBankAccount(BankAccountDTO dto);

    Integer createCustomer(CustomerDTO dto);

    Integer findPersonIdByCustomerId(Integer customerId);


}
