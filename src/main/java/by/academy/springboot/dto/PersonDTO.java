package by.academy.springboot.dto;

import by.academy.springboot.model.entity.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PersonDTO {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String citizenIdNumber;
    private String passportNumber;
    private List<AddressDTO> addresses;
    private int customerId;
    private int employeeId;
}
