package by.academy.springboot.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PaymentOrderDTO {
    private Integer id;
    private LocalDateTime timeStamp;
    private Double amount;
    //fromAccount
    private String fromAccountNumber;
    //--customer
    private Integer fromAccountCustomerId;
    // ----customer.person
    private String fromAccountCustomerFirstName;
    private String fromAccountCustomerMiddleName;
    private String fromAccountCustomerLastName;
    private LocalDate fromAccountCustomerDateOfBirth;
    private String fromAccountCustomerCitizenIdNumber;
    private String fromAccountCustomerPassportNumber;
    //toAccount
    private String toAccountNumber;
    //--customer
    private Integer toAccountCustomerId;
    // ----customer.person
    private String toAccountCustomerFirstName;
    private String toAccountCustomerMiddleName;
    private String toAccountCustomerLastName;
    private LocalDate toAccountCustomerDateOfBirth;
    private String toAccountCustomerCitizenIdNumber;
    private String toAccountCustomerPassportNumber;
}
