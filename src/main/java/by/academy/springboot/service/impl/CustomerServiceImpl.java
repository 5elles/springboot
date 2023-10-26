package by.academy.springboot.service.impl;

import by.academy.springboot.model.entity.*;
import by.academy.springboot.model.repository.*;
import by.academy.springboot.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;
    private final PaymentOrderRepository paymentOrderRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ContactRepository contactRepository;

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerById(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer findCustomerByPerson(Person person) {
        return customerRepository.findCustomerByPerson(person);
    }

    @Override
    public Customer findCustomerByPersonId(int id) {
        return customerRepository.findCustomerByPersonId(id);
    }

    @Override
    public Person findPersonById(int id) {
        return personRepository.findById(id).orElse(null);
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
    public BankAccount findBankAccountById(int id) {
        return bankAccountRepository.findById(id).orElse(null);
    }

    @Override
    public Customer findCustomerByID(Integer customerID) {
        return customerRepository.findById(customerID).orElse(null);
    }

    @Override
    public List<Person> findByLastNameLike(String lastNameLike) {
        return personRepository.findByLastNameLikeIgnoreCaseOrderByLastName(lastNameLike);
    }

    @Override
    public Contact findContactByPerson(Person person) {
        return contactRepository.findContactByPerson(person);
    }

    @Override
    public List<PaymentOrder> findAllPaymentOrdersSortedByDate() {
        List<PaymentOrder> orders = paymentOrderRepository.findAll();
        orders.sort(Comparator.comparing(PaymentOrder::getTimeStamp));
        return orders;
    }


    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void updateCustomer(int id, Customer updatedCustomer) {
        updatedCustomer.setId(id);
        customerRepository.save(updatedCustomer);
    }

    @Override
    @Transactional
    public void deleteCustomerById(int id) {
        customerRepository.deleteById(id);
    }
}
