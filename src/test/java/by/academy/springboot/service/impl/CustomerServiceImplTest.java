package by.academy.springboot.service.impl;

import by.academy.springboot.dto.CurrencyDTO;
import by.academy.springboot.dto.PaymentOrderDTO;
import by.academy.springboot.mapper.CurrencyListMapper;
import by.academy.springboot.model.entity.Currency;
import by.academy.springboot.model.entity.PaymentOrder;
import by.academy.springboot.model.repository.CurrencyRepository;
import by.academy.springboot.model.repository.PaymentOrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @Mock
    PaymentOrderRepository paymentOrderRepository;
    @Mock
    CurrencyRepository currencyRepository;
    @InjectMocks
    CustomerServiceImpl customerService;

    @DisplayName("test for findALlCurrencies method")
    @Test
    void givenCurrencyList_whenFindAllCurrencies_thenReturnCurrencyList() {
        Currency currency = Currency.builder()
                .currencyAbbreviation("USD")
                .id(1)
                .build();
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
        PaymentOrder order = PaymentOrder.builder()
                .id(1)
                .amount(1.0)
                .build();
        PaymentOrder order1 = PaymentOrder.builder()
                .id(2)
                .amount(2.0)
                .build();
        given(paymentOrderRepository.findAllOrders()).willReturn(List.of(order, order1));
        List<PaymentOrderDTO> allPaymentOrders = customerService.findAllPaymentOrders();
        assertThat(allPaymentOrders).isNotNull().hasSize(2);
    }


}