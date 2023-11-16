package by.academy.springboot.service.impl;

import by.academy.springboot.dto.*;
import by.academy.springboot.exception.ForbiddenActionException;
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
        List<Customer> customers = customerRepository.findAllOrderByPersonLastName();
        return CustomerListMapper.INSTANCE.toDTOList(customers);
    }

    @Override
    public CustomerFullDataDTO findFullData(Integer customerId) throws IncorrectParameterException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()->new IncorrectParameterException("wrong customer parameter, id " + customerId));
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
    public PaymentOrderDTO findById(int id) throws IncorrectParameterException {
        PaymentOrder order = paymentOrderRepository.findById(id)
                .orElseThrow(()->new IncorrectParameterException("wrong order id: " + id));
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
                .orElseThrow(() -> new IncorrectParameterException("no such bank account: id " + bankAccountId));
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
        List<PaymentOrder> orders = paymentOrderRepository.findAllOrders();
        return PaymentOrderListMapper.INSTANCE.toDTOList(orders);
    }

    @Transactional
    @Override
    public void closeAccount(int accountId) throws IncorrectParameterException, ForbiddenActionException {
        BankAccount account = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new IncorrectParameterException("no such bank account: id " + accountId));
        if (account.getCurrentBalance() != 0) {
            throw new ForbiddenActionException("current balance can not be above 0, account: id" + accountId);
        }
        account.setClosureDate(LocalDate.now());
        bankAccountRepository.save(account);
    }

    @Override
    @Transactional
    public void terminateContract(int customerId) throws IncorrectParameterException, ForbiddenActionException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IncorrectParameterException("no such customer: id " + customerId));
        if (!isReadyForTermination(customer)) {
            throw new ForbiddenActionException("contract can not be terminated, customer: id " + customerId);
        }
        customer.setClosureDate(LocalDate.now());
    }

    public boolean isReadyForTermination(Customer customer) {
        return customer.getAgreementDate() != null && !hasActiveBankAccounts(customer.getId());
    }

    @Override
    public boolean hasActiveBankAccounts(int customerID) {
        List<BankAccount> bankAccounts = bankAccountRepository.findBankAccountsByCustomerId(customerID);
        if (bankAccounts.isEmpty()) {
            return false;
        }
        for (BankAccount account : bankAccounts) {
            if (account.getClosureDate() != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    @Transactional
    public void createNewBankContract(CustomerDTO dto) throws IncorrectParameterException, ForbiddenActionException {
        Customer customer = customerRepository.findById(dto.getId())
                .orElseThrow(() -> new IncorrectParameterException("no such customer, id " + dto.getId()));
        if (isForbiddenForCreation(customer)) {
            throw new ForbiddenActionException("new contract creation is forbidden, check customer id " + dto.getId());
        }
        customer.setAgreementNumber(dto.getAgreementNumber());
        customer.setAgreementDate(dto.getAgreementDate());
        customer.setClosureDate(null);
    }

    @Override
    public boolean isForbiddenForCreation(Customer customer) {
        return customer == null || customer.getClosureDate() == null;
    }

    @Override
    public List<CurrencyDTO> findAllCurrencies() {
        List<Currency> currencies = currencyRepository.findAllOrderByCurrencyAbbreviation();
        return CurrencyListMapper.INSTANCE.toDTO(currencies);
    }

    @Transactional
    @Override
    public void createNewBankAccount(BankAccountDTO dto) {
        Customer customer = customerRepository.findById(dto.getCustomerID())
                .orElseThrow(() -> new IncorrectParameterException("no such customer, id " + dto.getCustomerID()));
        if (!isReadyForBankAccountCreation(customer, dto)) {
            throw new ForbiddenActionException("new bank account creation is forbidden! check the customer`s contract and validate the new account form");
        }
        bankAccountRepository.save(BankAccountMapper.INSTANCE.dtoToModel(dto));
    }

    @Override
    public boolean isReadyForBankAccountCreation(Customer customer, BankAccountDTO dto) {
        return customer.getClosureDate() == null
                && dto.getOpeningDate() != null
                && dto.getAccountNumber() != null
                && dto.getCurrencyID() != null
                && dto.getCustomerID() != null;
    }

    /**
     * @return new customer`s id or -1
     */
    @Override
    @Transactional
    public Integer createCustomer(CustomerDTO dto) {
        Person person = personRepository.findById(dto.getPersonId()).orElse(null);
        Customer customer = customerRepository.findCustomerByPerson(person);
        if (isForbiddenForCreation(person, customer)) {
            throw new ForbiddenActionException("new customer creation is forbidden. check dto or person, person id: " + dto.getPersonId());
        }
        return customerRepository.save(CustomerMapper.INSTANCE.toModel(dto)).getId();
    }

    @Override
    public boolean isForbiddenForCreation(Person person, Customer customer) {
        return person == null || customer != null;
    }

    /**
     * @return the id-number of the person or -1
     */
    @Override
    public Integer findPersonIdByCustomerId(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            return -1;
        }
        return customer.getPerson().getId();
    }
}
