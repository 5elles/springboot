package by.academy.springboot.service.impl;

import by.academy.springboot.dto.CustomerFullDataDTO;
import by.academy.springboot.dto.PaymentOrderDTO;
import by.academy.springboot.exception.IncorrectParameterException;
import by.academy.springboot.mapper.PaymentOrderMapper;
import by.academy.springboot.model.entity.*;
import by.academy.springboot.model.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.method.P;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IBankServiceImplTest {
    @InjectMocks
    private IBankServiceImpl iBankService;
    @Mock
    private PaymentOrderRepository paymentOrderRepository;
    @Mock
    private BankAccountRepository bankAccountRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ContactRepository contactRepository;
    @Mock
    private CurrencyRepository currencyRepository;

    private BankAccount account;
    private Currency currency;
    private Customer customer;
    private Person person;
    private Contact contact;


    @BeforeEach
    public void setup() {
        currency = Currency.builder()
                .currencyRate(1.5)
                .currencyName("dollar")
                .currencyCode("1")
                .currencyAbbreviation("D")
                .build();
        customer = Customer.builder()
                .id(1)
                .person(person)
                .build();

        account = BankAccount.builder()
                .id(1)
                .accountNumber("1")
                .openingDate(LocalDate.now())
                .closureDate(null)
                .currency(currency)
                .currentBalance(100.0)
                .customer(customer)
                .build();

        person = Person.builder()
                .id(1)
                .build();
        contact = Contact.builder()
                .id(1)
                .person(person)
                .build();
    }


    @DisplayName("test for findFullData method")
    @Test
    void givenCustomerIdAndContactAndCurrenciesList_whenFindFullData_thenReturnCustomerFullDataDto() {

        given(customerRepository.findById(1)).willReturn(Optional.of(customer));
        given(contactRepository.findByPerson(customer.getPerson())).willReturn(contact);
        given(currencyRepository.findAllByCurrencyAbbreviationIsNot("BYN")).willReturn(List.of(currency));
        CustomerFullDataDTO fullData = iBankService.findFullData(1);
        assertThat(fullData)
                .isNotNull()
                .isInstanceOf(CustomerFullDataDTO.class);
    }

    @DisplayName("test for findFullData method which throws IncorrectParameterException")
    @Test
    void givenCustomerIdAndContactAndCurrenciesList_whenFindFullData_thenThrowsIncorrectParameterException() {
        given(customerRepository.findById(5))
                .willThrow(IncorrectParameterException.class);
        org.junit.jupiter.api.Assertions.assertThrows(IncorrectParameterException.class,
                () -> iBankService.findFullData(5));
    }

    @DisplayName("test for closeBankAccount method")
    @Test
    void givenBankAccountId_whenCloseBankAccount_thenAccountClosureDateIsNotNull() {
        account.setCurrentBalance(0.0);
        given(bankAccountRepository.findById(account.getId())).willReturn(Optional.of(account));
        iBankService.closeBankAccount(account.getId());
        assertThat(account.getClosureDate()).isNotNull();
    }

    @DisplayName("test for closeBankAccount method witch throws IncorrectParameterException")
    @Test
    void givenBankAccountId_whenCloseBankAccount_thenThrowsIncorrectParameterException() {
        given(bankAccountRepository.findById(4))
                .willThrow(IncorrectParameterException.class);
        org.junit.jupiter.api.Assertions.assertThrows(IncorrectParameterException.class,
                () -> iBankService.closeBankAccount(4));
    }

    @DisplayName("test for findByFromAccountNumber method")
    @Test
    void givenBankAccountNumber_whenFindByFromAccountNumber_thenReturnPaymentOrderDTOList() {
        BankAccount account1 = BankAccount.builder()
                .id(2)
                .accountNumber("321")
                .build();
        PaymentOrder order = PaymentOrder.builder()
                .id(1)
                .fromAccount(account)
                .toAccount(account1)
                .build();
        given(paymentOrderRepository.findAll(account.getAccountNumber())).willReturn(List.of(order));
        List<PaymentOrderDTO> byFromAccountNumber = iBankService.findByFromAccountNumber(account.getAccountNumber());
        assertThat(byFromAccountNumber)
                .hasSize(1);
        assertThat(byFromAccountNumber.get(0))
                .isEqualTo(PaymentOrderMapper.INSTANCE.toDTO(order));
    }

    @DisplayName("test for isForbiddenForExecution method")
    @Test
    void givenBankAccountWithCurrentBalanceGreaterZero_whenIsForbiddenForExecution_thenTrue() {
        account.setCurrentBalance(12.0);
        assertThat(iBankService.isForbiddenForExecution(account)).isTrue();
    }


    @DisplayName("test for save method")
    @Test
    void givenPaymentOrder_whenSave_thenDoNothing() {
        PaymentOrder paymentOrder = PaymentOrder.builder()
                .id(1)
                .build();
        given(paymentOrderRepository.save(any(PaymentOrder.class))).willReturn(any(PaymentOrder.class));
        iBankService.save(paymentOrder);
        verify(paymentOrderRepository, times(1)).save(paymentOrder);
    }
}