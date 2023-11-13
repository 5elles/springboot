package by.academy.springboot.service.impl;

import by.academy.springboot.dto.*;
import by.academy.springboot.exception.IncorrectParameterException;
import by.academy.springboot.mapper.*;
import by.academy.springboot.model.entity.*;
import by.academy.springboot.model.repository.*;
import by.academy.springboot.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;
    private final PaymentOrderRepository paymentOrderRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ContactRepository contactRepository;
    private final CurrencyRepository currencyRepository;

    @Override
    public List<CustomerDTO> findAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        customers.sort(Comparator.comparing(o -> o.getPerson().getLastName()));
        return CustomerListMapper.INSTANCE.toDTOList(customers);
    }

    @Override
    public CustomerFullDataDTO findFullData(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            return null;
        }
        Contact contact = contactRepository.findByPerson(customer.getPerson());
        List<Currency> currencies = currencyRepository.findAllByCurrencyAbbreviationIsNot("BYN");
        return CustomerFullDataMapper.INSTANCE.modelsToDTO(customer, contact, currencies);
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
    public BankAccountFullDataDTO findBankAccountFullData(int bankAccountId) throws IncorrectParameterException {
        BankAccount account = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new IncorrectParameterException("no such bank account: id" + bankAccountId));

        String accountNumber = account.getAccountNumber();
        List<PaymentOrderDTO> incomingPayments = findByToAccountNumber(accountNumber);
        List<PaymentOrderDTO> allPayments = findAllOutgoingAndIncoming(accountNumber, accountNumber);
        List<PaymentOrderDTO> outgoingPayments = findByFromAccountNumber(accountNumber);
        return BankAccountFullDataMapper.INSTANCE.modelsToDTO(
                account,
                allPayments,
                outgoingPayments,
                incomingPayments
        );
    }

    @Override
    public List<PaymentOrderDTO> findAllPaymentOrders() {
        List<PaymentOrder> orders = paymentOrderRepository.findAll();
        orders.sort(Comparator.comparing(PaymentOrder::getTimeStamp));
        return PaymentOrderListMapper.INSTANCE.toDTOList(orders);
    }

    @Transactional
    @Override
    public boolean closeAccount(int accountId) {
        BankAccount account = bankAccountRepository.findById(accountId).orElse(null);
        if (
                account == null
                        || account.getCurrentBalance() != 0
        ) {
            return false;
        }
        account.setClosureDate(LocalDate.now());
        return true;
    }

    @Override
    @Transactional
    public boolean terminateContract(int customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null
                || customer.getAgreementDate() == null
                || hasActiveBankAccounts(customerId)
        ) {
            return false;
        }
        customer.setClosureDate(LocalDate.now());
        return true;
    }

    @Override
    public boolean hasActiveBankAccounts(int customerID) {
        List<BankAccount> bankAccounts = bankAccountRepository.findBankAccountsByCustomerId(customerID);
        for (BankAccount account : bankAccounts) {
            if (account.getClosureDate() != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    @Transactional
    public boolean createNewBankContract(CustomerDTO dto) {
        Customer customer = customerRepository.findById(dto.getId()).orElse(null);
        if (customer == null
                || customer.getClosureDate() == null
        ) {
            return false;
        }
        customer.setAgreementNumber(dto.getAgreementNumber());
        customer.setAgreementDate(dto.getAgreementDate());
        customer.setClosureDate(null);
        return true;
    }

    @Override
    public List<CurrencyDTO> findAllCurrencies() {
        List<Currency> currencies = currencyRepository.findAll();
        currencies.sort(Comparator.comparing(Currency::getCurrencyName));
        return CurrencyListMapper.INSTANCE.toDTO(currencies);
    }

    @Transactional
    @Override
    public boolean createNewBankAccount(BankAccountDTO dto) {
        Customer customer = customerRepository.findById(dto.getCustomerID()).orElse(null);
        if (customer == null
                || customer.getClosureDate() != null
                || dto.getOpeningDate() == null
                || dto.getAccountNumber() == null
                || dto.getCurrencyID() == null
                || dto.getCustomerID() == null) {
            return false;
        }
        bankAccountRepository.save(BankAccountMapper.INSTANCE.dtoToModel(dto));
        return true;
    }

    /**
     * @return new customer`s id or -1
     */
    @Override
    @Transactional
    public Integer createCustomer(CustomerDTO dto) {
        Person person = personRepository.findById(dto.getPersonId()).orElse(null);
        Customer customer = customerRepository.findCustomerByPerson(person);
        if (person == null
                || customer != null) {
            return -1;
        }
        return customerRepository.save(CustomerMapper.INSTANCE.toModel(dto)).getId();
    }

    /**
     * @return the id-number of the person or -1
     */
    @Override
    public Integer findPersonIdByCustomerId(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null){
            return -1;
        }
        return customer.getPerson().getId();
    }
}
