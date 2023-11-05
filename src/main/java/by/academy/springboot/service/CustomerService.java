package by.academy.springboot.service;

import by.academy.springboot.dto.*;
import by.academy.springboot.model.entity.*;

import java.time.LocalDate;
import java.util.List;

public interface CustomerService {
    List<CustomerDTO> findAllCustomers();

    CustomerDTO findCustomerById(int id);

    CustomerFullDataDTO findFullData(Integer customerId);

    PersonDTO findPersonById(int id);

    List<PaymentOrderDTO> findAllOutgoingAndIncoming(String outgoingAccountNumber, String incomingAccountNumber);

    PaymentOrderDTO findById(int id);

    List<PaymentOrderDTO> findByFromAccountNumber(String accountNumber);

    List<PaymentOrderDTO> findByToAccountNumber(String accountNumber);

    BankAccountDTO findBankAccountById(int id);

    BankAccountFullDataDTO findBankAccountFullData(int bankAccountId);

    ContactDTO findContact(Person person);

    List<PaymentOrderDTO> findAllPaymentOrders();

    int save(PersonDTO dto);

    AddressFullDataDTO findFullData();

    List<PersonDTO> findByLastNameOrFirstNameOrMiddleNameOrDOBOrCitizenID(String lastName,
                                                                          String firstName,
                                                                          String middleName
    );


}
