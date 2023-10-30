package by.academy.springboot.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BankAccountDTO {
    private Integer id;
    private String accountNumber;
    private LocalDate openingDate;
    private LocalDate closureDate;
    private Double currentBalance;
    private String currencyAbbreviation;
    private Double currencyRate;
}
