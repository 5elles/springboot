package by.academy.springboot.service.impl;

import by.academy.springboot.dto.PaymentOrderDTO;
import by.academy.springboot.mapper.PaymentOrderMapper;
import by.academy.springboot.model.entity.BankAccount;
import by.academy.springboot.model.entity.Currency;
import by.academy.springboot.model.entity.Customer;
import by.academy.springboot.model.entity.PaymentOrder;
import by.academy.springboot.model.repository.*;
import by.academy.springboot.service.IBankService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class IBankServiceImplTest {
    @InjectMocks
    private IBankServiceImpl iBankService;
    @Mock
    private PaymentOrderRepository paymentOrderRepository;

    private PaymentOrder order;
    private PaymentOrderDTO orderDTO;
    private BankAccount fromAccount;
    private BankAccount toAccount;
    private Currency currency;
    private Customer customerA;
    private Customer customerB;


    @BeforeEach
    public void setup(){
        paymentOrderRepository = Mockito.mock(PaymentOrderRepository.class);
        currency = Currency.builder()
                .currencyRate(1.5)
                .currencyName("dollar")
                .currencyCode("1")
                .currencyAbbreviation("D")
                .build();
        customerA = Customer.builder()
                .id(1)
                .build();

        customerB = Customer.builder()
                .id(2)
                .build();

        fromAccount = BankAccount.builder()
                .id(1)
                .accountNumber("1")
                .openingDate(LocalDate.now())
                .closureDate(null)
                .currency(currency)
                .currentBalance(100.0)
                .customer(customerA)
                .build();

        toAccount = BankAccount.builder()
                .id(2)
                .accountNumber("2")
                .openingDate(LocalDate.now())
                .closureDate(null)
                .currency(currency)
                .currentBalance(200.0)
                .customer(customerB)
                .build();


        order = PaymentOrder.builder()
                .id(1)
                .timeStamp(LocalDateTime.now())
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .amount(1.0)
                .build();
    }


//    @ParameterizedTest
//    void save_Should_Return_True() {
////        given()
//    }
//
//    @DisplayName("test for findByFromAccountNumber")
//    @Test
//    void givenOrdersList_whenFindByFromAccountNumber_thenReturnOrdersListWithOrder() {
//        orderDTO = PaymentOrderMapper.INSTANCE.toDTO(order);
////        PaymentOrder order1 = PaymentOrder.builder()
////                .fromAccount(BankAccount.builder()
////                        .accountNumber("200")
////                        .build())
////                .toAccount(fromAccount)
////                .build();
//        given(iBankService.findByFromAccountNumber("1"))
//                .willReturn(List.of(orderDTO));
//        List<PaymentOrderDTO> orders = iBankService.findByFromAccountNumber("1");
//        assertThat(orders).isNotNull();
//        assertThat(orders.get(0)).isEqualTo(orderDTO);
//
//    }
}