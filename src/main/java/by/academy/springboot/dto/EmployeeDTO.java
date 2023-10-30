package by.academy.springboot.dto;

import by.academy.springboot.model.entity.Address;
import by.academy.springboot.model.entity.Person;
import by.academy.springboot.model.entity.WageRate;
import jakarta.persistence.*;
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
    private List<WageRateDTO> wageRates;
}
