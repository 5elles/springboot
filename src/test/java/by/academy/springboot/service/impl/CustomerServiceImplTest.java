package by.academy.springboot.service.impl;

import by.academy.springboot.dto.CurrencyDTO;
import by.academy.springboot.dto.CustomerDTO;
import by.academy.springboot.dto.CustomerFullDataDTO;
import by.academy.springboot.dto.PaymentOrderDTO;
import by.academy.springboot.exception.ForbiddenActionException;
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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @Mock
    PaymentOrderRepository paymentOrderRepository;
    @Mock
    CurrencyRepository currencyRepository;
    @Mock
    CustomerRepository customerRepository;
    @Mock
    ContactRepository contactRepository;
    @Mock
    BankAccountRepository bankAccountRepository;

    @InjectMocks
    CustomerServiceImpl customerService;
    private Person person;
    private Customer customer;
    private Contact contact;
    private Currency currency;
    private PaymentOrder order;
    private BankAccount account;

    @BeforeEach
    public void setup(){
        person = Person.builder()
                .id(1)
                .build();
        customer = Customer.builder()
                .id(1)
                .person(person)
                .build();
        contact = Contact.builder()
                .id(1)
                .person(person)
                .build();
        currency = Currency.builder()
                .id(1)
                .currencyAbbreviation("USD")
                .build();
        order = PaymentOrder.builder()
                .id(1)
                .amount(1.0)
                .build();
        account = BankAccount.builder()
                .id(1)
                .currentBalance(0.0)
                .build();
    }


    @DisplayName("test for findALlCurrencies method")
    @Test
    void givenCurrencyList_whenFindAllCurrencies_thenReturnCurrencyList() {
        Currency currency1 = Currency.builder()
                .currencyAbbreviation("BYN")
                .id(2)
                .build();
        given(currencyRepository.findAllOrderByCurrencyAbbreviation()).willReturn(List.of(currency, currency1));
        List<CurrencyDTO> allCurrencies = customerService.findAllCurrencies();
        assertThat(allCurrencies).isNotNull().hasSize(2);
    }

    @DisplayName("test for findAllPaymentOrders method")
    @Test
    void givenPaymentOrdersList_whenFindAllPaymentOrders_thenReturnPaymentOrdersList() {
        PaymentOrder order1 = PaymentOrder.builder()
                .id(2)
                .amount(2.0)
                .build();
        given(paymentOrderRepository.findAllOrders()).willReturn(List.of(order, order1));
        List<PaymentOrderDTO> allPaymentOrders = customerService.findAllPaymentOrders();
        assertThat(allPaymentOrders).isNotNull().hasSize(2);
    }

    @DisplayName("test for findALlCustomers method")
    @Test
    void givenCustomersList_whenFindAllCustomers_thenReturnCustomerDTOListHasSizeTwo() {
        Customer customer1 = Customer.builder()
                .id(2)
                .build();
        given(customerRepository.findAllOrderByPersonLastName()).willReturn(List.of(customer, customer1));
        List<CustomerDTO> allCustomers = customerService.findAllCustomers();
        assertThat(allCustomers).hasSize(2);
    }

    @DisplayName("test for findFullData method")
    @Test
    void givenCustomerIdAndContactAndCurrencies_whenFindFullData_thenReturnsFullDataDto() {
        given(customerRepository.findById(1)).willReturn(Optional.of(customer));
        given(contactRepository.findByPerson(person)).willReturn(contact);
        given(currencyRepository.findAllByCurrencyAbbreviationIsNot("BYN")).willReturn(List.of(currency));
        CustomerFullDataDTO fullData = customerService.findFullData(1);
        assertThat(fullData)
                .isNotNull()
                .isInstanceOf(CustomerFullDataDTO.class);
    }

    @DisplayName("test for findById method")
    @Test
    void givenPaymentOrderId_whenFindById_thenPaymentOrderDto() {
        given(paymentOrderRepository.findById(1)).willReturn(Optional.of(order));
        PaymentOrderDTO byId = customerService.findById(1);
        assertThat(byId)
                .isNotNull()
                .isInstanceOf(PaymentOrderDTO.class)
                .isEqualTo(PaymentOrderMapper.INSTANCE.toDTO(order));
    }

    @DisplayName("test for findById method which throws exception")
    @Test
    void givenPaymentOrderId_whenFindById_thenThrowsIncorrectParameterException() {
        given(paymentOrderRepository.findById(3)).willThrow(new IncorrectParameterException("exception"));
        org.junit.jupiter.api.Assertions.assertThrows(IncorrectParameterException.class,
                ()-> customerService.findById(3));
    }

    @DisplayName("test for closeAccount method")
    @Test
    void givenBankAccountId_whenCloseAccount_thenClosureDateIsNotNull() {
        given(bankAccountRepository.findById(1)).willReturn(Optional.of(account));
        customerService.closeAccount(1);
        assertThat(account.getClosureDate()).isNotNull();
    }

    @DisplayName("test for closeAccount which throws IncorrectParameterException")
    @Test
    void givenBankAccountId_whenCloseAccount_thenThrowsIncorrectParameterException() {
        given(bankAccountRepository.findById(3)).willThrow(new IncorrectParameterException("wrong id"));
        org.junit.jupiter.api.Assertions.assertThrows(IncorrectParameterException.class,
                ()-> customerService.closeAccount(3));
    }

    @DisplayName("test for closeAccount which throws ForbiddenActionException")
    @Test
    void givenBankAccountId_whenCloseAccount_thenThrowsForbiddenActionException() {
        account.setCurrentBalance(1.0);
        given(bankAccountRepository.findById(1)).willReturn(Optional.of(account));
        org.junit.jupiter.api.Assertions.assertThrows(ForbiddenActionException.class,
                ()-> customerService.closeAccount(1));
    }
}