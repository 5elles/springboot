package by.academy.springboot.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EmployeeDTO {
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String citizenIdNumber;
    private String passportNumber;
    private List<AddressDTO> addresses;
    private List<WageRateFullDataDTO> wageRates;
}
