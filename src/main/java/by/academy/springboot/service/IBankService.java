package by.academy.springboot.service;

import by.academy.springboot.dto.BankAccountFullDataDTO;
import by.academy.springboot.dto.CustomerFullDataDTO;
import by.academy.springboot.dto.OrderDTO;
import by.academy.springboot.dto.PaymentOrderDTO;
import by.academy.springboot.model.entity.BankAccount;
import by.academy.springboot.model.entity.Currency;
import by.academy.springboot.model.entity.Customer;
import by.academy.springboot.model.entity.PaymentOrder;

import java.util.List;


public interface IBankService {

    CustomerFullDataDTO findFullData(Integer customerId);
    BankAccountFullDataDTO findBankAccountFullData(int id);
    List<PaymentOrderDTO> findAllOutgoingAndIncoming(String outgoingAccountNumber, String incomingAccountNumber);
    List<PaymentOrderDTO> findByFromAccountNumber(String accountNumber);
    List<PaymentOrderDTO> findByToAccountNumber(String accountNumber);
    void save(PaymentOrder order);
    boolean save(OrderDTO order);
    boolean closeBankAccount(int accountId);
}
