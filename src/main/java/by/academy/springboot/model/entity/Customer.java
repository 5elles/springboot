package by.academy.springboot.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
    @Column(name = "agreement_number")
    private String agreementNumber;
    @Column(name = "agreement_date")
    private LocalDate agreementDate;
    @Column(name = "closure_date")
    private LocalDate closureDate;
    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<BankAccount> bankAccounts;

}
