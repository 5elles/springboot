package by.academy.springboot.dto;

import lombok.Data;


import java.time.LocalDate;
import java.util.List;

@Data
public class EmployeeFullDataDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String citizenIdNumber;
    private String passportNumber;
    private List<AddressDTO> addresses;
    private List<EmailDTO> emails;
    private List<PhoneNumberDTO> phoneNumbers;
    private List<WageRateDTO> wageRates;
}
