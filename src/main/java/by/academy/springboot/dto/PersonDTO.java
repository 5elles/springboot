package by.academy.springboot.dto;

import by.academy.springboot.model.entity.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class PersonDTO {
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String citizenIdNumber;
    private String passportNumber;
    private List<AddressDTO> addresses;
    private Integer customerId;
    private Integer employeeId;
    private Set<Role> roles;
}
