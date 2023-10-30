package by.academy.springboot.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PaymentOrderDTO {
    private Integer id;
    private LocalDateTime timeStamp;
    private Double amount;
    private String fromAccountNumber;
    private String fromAccountCurrencyAbbreviation;
    private Double fromAccountCurrencyRate;
    private Integer fromAccountCustomerId;
    private String fromAccountCustomerFirstName;
    private String fromAccountCustomerMiddleName;
    private String fromAccountCustomerLastName;
    private LocalDate fromAccountCustomerDateOfBirth;
    private String fromAccountCustomerCitizenIdNumber;
    private String fromAccountCustomerPassportNumber;
    private String toAccountNumber;
    private String toAccountCurrencyAbbreviation;
    private Double toAccountCurrencyRate;
    private Integer toAccountCustomerId;
    private String toAccountCustomerFirstName;
    private String toAccountCustomerMiddleName;
    private String toAccountCustomerLastName;
    private LocalDate toAccountCustomerDateOfBirth;
    private String toAccountCustomerCitizenIdNumber;
    private String toAccountCustomerPassportNumber;
}
