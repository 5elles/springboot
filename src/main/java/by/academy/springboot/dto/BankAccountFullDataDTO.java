package by.academy.springboot.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BankAccountFullDataDTO {
    private Integer id;
    private String accountHolderFirstName;
    private String accountHolderMiddleName;
    private String accountHolderLastName;
    private LocalDate accountHolderDateOfBirth;
    private String accountHolderCitizenIdNumber;
    private String accountHolderPassportNumber;
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
