package by.academy.springboot.service.impl;

import by.academy.springboot.dto.BankAccountFullDataDTO;
import by.academy.springboot.dto.CustomerFullDataDTO;
import by.academy.springboot.dto.PaymentOrderDTO;
import by.academy.springboot.mapper.BankAccountFullDataMapper;
import by.academy.springboot.mapper.CustomerFullDataMapper;
import by.academy.springboot.mapper.PaymentOrderMapper;
import by.academy.springboot.model.entity.*;
import by.academy.springboot.model.repository.*;
import by.academy.springboot.service.IBankService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IBankServiceImpl implements IBankService {
    private final BankAccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;
    private final CurrencyRepository currencyRepository;
    private final PaymentOrderRepository paymentOrderRepository;
    private final ContactRepository contactRepository;

    @Override
    public CustomerFullDataDTO findFullData(Integer customerId) {
        Customer iBankCustomer = customerRepository.findById(customerId).orElse(null);
        if (iBankCustomer == null) {
            return null;
        }
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
}

