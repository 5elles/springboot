package by.academy.springboot.service.impl;

import by.academy.springboot.dto.BankAccountFullDataDTO;
import by.academy.springboot.dto.CustomerFullDataDTO;
import by.academy.springboot.dto.OrderDTO;
import by.academy.springboot.dto.PaymentOrderDTO;
import by.academy.springboot.exception.ForbiddenActionException;
import by.academy.springboot.exception.IncorrectParameterException;
import by.academy.springboot.mapper.BankAccountFullDataMapper;
import by.academy.springboot.mapper.CustomerFullDataMapper;
import by.academy.springboot.mapper.PaymentOrderListMapper;
import by.academy.springboot.mapper.PaymentOrderMapper;
import by.academy.springboot.model.entity.*;
import by.academy.springboot.model.repository.*;
import by.academy.springboot.service.IBankService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class IBankServiceImpl implements IBankService {
    private final BankAccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;
    private final CurrencyRepository currencyRepository;
    private final PaymentOrderRepository paymentOrderRepository;
    private final ContactRepository contactRepository;
    private final PersonRepository personRepository;
    private final UserRepository userRepository;

    @Override
    public CustomerFullDataDTO findFullData(Integer customerId) throws IncorrectParameterException {
        Customer iBankCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IncorrectParameterException("no such customer, id " + customerId));
        Contact contact = contactRepository.findByPerson(iBankCustomer.getPerson());
        List<Currency> currencies = currencyRepository.findAllByCurrencyAbbreviationIsNot("BYN");
        return CustomerFullDataMapper.INSTANCE.modelsToDTO(iBankCustomer, contact, currencies);
    }

    @Override
    public BankAccountFullDataDTO findBankAccountFullData(int id) {
        BankAccount account = bankAccountRepository.findById(id).orElse(null);
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
    public List<PaymentOrderDTO> findAllOutgoingAndIncoming(String outgoingAccountNumber, String incomingAccountNumber) {
        List<PaymentOrder> orders = paymentOrderRepository
                .findDistinctByFromAccount_AccountNumberOrToAccount_AccountNumberOrderByTimeStamp(outgoingAccountNumber, incomingAccountNumber);
        return orders.stream()
                .map(PaymentOrderMapper.INSTANCE::toDTO)
                .toList();
    }

    @Override
    public List<PaymentOrderDTO> findByFromAccountNumber(String accountNumber) {
        List<PaymentOrder> order = paymentOrderRepository.findAll(accountNumber);
        return PaymentOrderListMapper.INSTANCE.toDTOList(order);
    }

    @Override
    public List<PaymentOrderDTO> findByToAccountNumber(String accountNumber) {
        List<PaymentOrder> orders = paymentOrderRepository.findPaymentOrdersByToAccount_AccountNumberOrderByTimeStamp(accountNumber);
        return orders.stream()
                .map(PaymentOrderMapper.INSTANCE::toDTO)
                .toList();
    }

    @Override
    public void save(PaymentOrder order) {
        paymentOrderRepository.save(order);
    }

    @Transactional
    @Override
    public void save(OrderDTO order) throws IncorrectParameterException, ForbiddenActionException {
        BankAccount fromBankAccount = bankAccountRepository.findById(order.getFromAccountId())
                .orElseThrow(() -> new IncorrectParameterException("no such from account!"));
        BankAccount toBankAccount = bankAccountRepository.findBankAccountByAccountNumber(order.getToAccountNumber());
        if (isForbiddenForExecution(fromBankAccount, toBankAccount, order)) {
            throw new ForbiddenActionException("operation is forbidden. check your parameters!");
        }
        double amount = (double) Math.round(order.getAmount() * 100) / 100;
        PaymentOrder paymentOrder = PaymentOrder.builder()
                .amount(amount)
                .timeStamp(LocalDateTime.now())
                .fromAccount(fromBankAccount)
                .toAccount(toBankAccount)
                .build();
        save(paymentOrder);
        fromBankAccount.setCurrentBalance(fromBankAccount.getCurrentBalance() - amount);
        toBankAccount.setCurrentBalance(toBankAccount.getCurrentBalance() + amount);
        bankAccountRepository.save(fromBankAccount);
        bankAccountRepository.save(toBankAccount);
    }

    @Override
    public boolean isForbiddenForExecution(BankAccount fromAccount, BankAccount toAccount, OrderDTO order) {
        return toAccount == null
                || fromAccount == null
                || !fromAccount.getCurrency().getCurrencyAbbreviation().equals(toAccount.getCurrency().getCurrencyAbbreviation())
                || toAccount.getClosureDate() != null
                || fromAccount.getClosureDate() != null
                || fromAccount.getCurrentBalance() < order.getAmount();
    }

    @Transactional
    @Override
    public void closeBankAccount(int accountId) throws ForbiddenActionException, IncorrectParameterException {
        BankAccount account = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new IncorrectParameterException("no such bank account, id " + accountId));
        if (isForbiddenForExecution(account)) {
            throw new ForbiddenActionException("bank account can not be null or has current balance > 0");
        }
        account.setClosureDate(LocalDate.now());
    }

    @Override
    public boolean isForbiddenForExecution(BankAccount account) {
        return account == null ||
                account.getCurrentBalance() != 0;
    }

    @Override
    public boolean isAllowedToHaveAccess(UserDetails details, Integer customerIdToAccess) {
        Person person = personRepository.findPerson(userRepository.getUserByUsername(details.getUsername()));
        Customer customer = person.getCustomer();
        if (customer == null || !Objects.equals(customerIdToAccess, customer.getId())) {
            throw new ForbiddenActionException("access denied");
        }
        return true;
    }
    @Override
    public boolean isAllowedToAccountAccess(UserDetails details, Integer accountIdToAccess)
    throws ForbiddenActionException {
        User user = userRepository.getUserByUsername(details.getUsername());
        Customer customer = personRepository.findPerson(user).getCustomer();
        List<BankAccount> bankAccounts = customer.getBankAccounts();
        boolean match = bankAccounts.stream()
                .anyMatch(account -> account.getId().equals(accountIdToAccess));
        if (!match) {
            throw new ForbiddenActionException("access denied");
        }
        return true;
    }
}

