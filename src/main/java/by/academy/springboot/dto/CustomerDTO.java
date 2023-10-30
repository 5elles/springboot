package by.academy.springboot.dto;


import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CustomerDTO {
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String citizenIdNumber;
    private String passportNumber;
    private List<AddressDTO> addresses;
    private String agreementNumber;
    private LocalDate agreementDate;
    private LocalDate closureDate;
    private List<BankAccountDTO> bankAccounts;
}
