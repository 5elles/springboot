package by.academy.springboot.service;

import by.academy.springboot.model.entity.*;

import java.util.List;

public interface CustomerService {
    List<Customer> findAllCustomers();
    Customer findCustomerById(int id);
    Customer findCustomerByPerson(Person person);
    Customer findCustomerByPersonId(int id);
    Person findPersonById(int id);
    List<PaymentOrder> findDistinctByFromAccount_AccountNumberOrToAccount_AccountNumberOrderByTimeStamp(String accountNumber1, String accountNumber2);
    PaymentOrder findPaymentOrderById(int id);
    List<PaymentOrder> findPaymentOrdersByFromAccount_AccountNumberOrderByTimeStamp(String accountNumber);
    List<PaymentOrder> findPaymentOrdersByToAccount_AccountNumberOrderByTimeStamp(String accountNumber);
    BankAccount findBankAccountById(int id);
    Customer findCustomerByID(Integer customerID);
    List<Person> findByLastNameLike(String lastNameLike);
    Contact findContactByPerson(Person person);
    List<PaymentOrder> findAllPaymentOrdersSortedByDate();

    void saveCustomer(Customer customer);
    void updateCustomer(int id, Customer updatedCustomer);
    void deleteCustomerById(int id);
}
