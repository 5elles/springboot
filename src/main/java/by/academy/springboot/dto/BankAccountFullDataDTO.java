package by.academy.springboot.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BankAccountFullDataDTO {
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String citizenIdNumber;
    private String passportNumber;
    private String accountNumber;
    private LocalDate openingDate;
    private LocalDate closureDate;
    private Double currentBalance;
    private String currencyAbbreviation;
    private Double currencyRate;

    private List<PaymentOrderDTO> allPayments;
    private List<PaymentOrderDTO> incomingPayments;
    private List<PaymentOrderDTO> outgoingPayments;
}
