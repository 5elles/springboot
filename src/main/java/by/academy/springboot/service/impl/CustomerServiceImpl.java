package by.academy.springboot.service.impl;

import by.academy.springboot.dto.*;
import by.academy.springboot.mapper.*;
import by.academy.springboot.model.entity.*;
import by.academy.springboot.model.repository.*;
import by.academy.springboot.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;
    private final PaymentOrderRepository paymentOrderRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ContactRepository contactRepository;

    @Override
    public List<CustomerDTO> findAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        customers.sort(Comparator.comparing(o -> o.getPerson().getLastName()));
        return CustomerListMapper.INSTANCE.toDTOList(customers);
    }

    @Override
    public CustomerDTO findCustomerById(int id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return null;
        }
        return CustomerMapper.INSTANCE.modelToDTO(customer);
    }

    @Override
    public CustomerFullDataDTO findFullData(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            return null;
        }
        Contact contact = contactRepository.findByPerson(customer.getPerson());
        return CustomerFullDataMapper.INSTANCE.modelsToDTO(customer, contact);
    }

    @Override
    public PersonDTO findPersonById(int id) {
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            return null;
        }
        return PersonMapper.INSTANCE.toDTO(person);
    }

    @Override
    public List<PaymentOrderDTO> findAllOutgoingAndIncoming(String outgoingAccountNumber, String incomingAccountNumber) {
        List<PaymentOrder> orders = paymentOrderRepository
                .findDistinctByFromAccount_AccountNumberOrToAccount_AccountNumberOrderByTimeStamp(outgoingAccountNumber, incomingAccountNumber);
        return orders.stream()
                .map(PaymentOrderMapper.INSTANCE::toDTO)
                .toList();
    }

    @Override
    public PaymentOrderDTO findById(int id) {
        PaymentOrder order = paymentOrderRepository.findById(id).orElse(null);
        if (order == null) {
            return null;
        }
        return PaymentOrderMapper.INSTANCE.toDTO(order);
    }

    @Override
    public List<PaymentOrderDTO> findByFromAccountNumber(String accountNumber) {
        List<PaymentOrder> order = paymentOrderRepository.findPaymentOrdersByFromAccount_AccountNumberOrderByTimeStamp(accountNumber);
        return order.stream()
                .map(PaymentOrderMapper.INSTANCE::toDTO)
                .toList();
    }

    @Override
    public List<PaymentOrderDTO> findByToAccountNumber(String accountNumber) {
        List<PaymentOrder> orders = paymentOrderRepository.findPaymentOrdersByToAccount_AccountNumberOrderByTimeStamp(accountNumber);
        return orders.stream()
                .map(PaymentOrderMapper.INSTANCE::toDTO)
                .toList();
    }

    @Override
    public BankAccountDTO findBankAccountById(int id) {
        BankAccount account = bankAccountRepository.findById(id).orElse(null);
        if (account == null) {
            return null;
        }
        return BankAccountMapper.INSTANCE.modelToDTO(account);
    }

    @Override
    public BankAccountFullDataDTO findBankAccountFullData(int bankAccountId) {
        BankAccount account = bankAccountRepository.findById(bankAccountId).orElse(null);
        if (account == null) {
            return null;
        }
        String accountNumber = account.getAccountNumber();
        List<PaymentOrderDTO> allPayments = findAllOutgoingAndIncoming(accountNumber, accountNumber);
        List<PaymentOrderDTO> outgoingPayments = findByFromAccountNumber(accountNumber);
        List<PaymentOrderDTO> incomingPayments = findByToAccountNumber(accountNumber);
        return BankAccountFullDataMapper.INSTANCE.modelsToDTO(
                account,
                allPayments,
                outgoingPayments,
                incomingPayments
        );
    }


    @Override
    public ContactDTO findContact(Person person) {
        Contact contact = contactRepository.findByPerson(person);
        return ContactMapper.INSTANCE.toDTO(contact);
    }

    @Override
    public List<PaymentOrderDTO> findAll() {
        List<PaymentOrder> orders = paymentOrderRepository.findAll();
        orders.sort(Comparator.comparing(PaymentOrder::getTimeStamp));
        return PaymentOrderListMapper.INSTANCE.toDTOList(orders);
    }

//    @Override
//    @Transactional
//    public void saveCustomer(Customer customer) {
//        customerRepository.save(customer);
//    }
//
//    @Override
//    @Transactional
//    public void updateCustomer(int id, Customer updatedCustomer) {
//        updatedCustomer.setId(id);
//        customerRepository.save(updatedCustomer);
//    }
//
//    @Override
//    @Transactional
//    public void deleteCustomerById(int id) {
//        customerRepository.deleteById(id);
//    }
}
