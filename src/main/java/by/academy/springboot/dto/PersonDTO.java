package by.academy.springboot.dto;

import by.academy.springboot.model.entity.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PersonDTO {

    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String citizenIdNumber;
    private String passportNumber;
    private Integer isStaff;
    private Integer isClient;
    private List<AddressDTO> addresses;
}
