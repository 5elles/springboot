package by.academy.springboot.model.entity;

import by.academy.springboot.model.entity.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "dob")
    private LocalDate dateOfBirth;
    @Column(name = "citizenID")
    private String citizenIdNumber;
    @Column(name = "passport_number")
    private String passportNumber;
    @Column (name = "login")
    private String login;
    @Column (name = "password")
    private String password;
    @OneToMany
    @JoinColumn(name = "person_id")
    private List<Address> addresses;
    @OneToOne(mappedBy = "person")
    private Customer customer;
    @OneToOne(mappedBy = "person")
    private Employee employee;
}
