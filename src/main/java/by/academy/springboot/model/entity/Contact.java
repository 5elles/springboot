package by.academy.springboot.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
    @OneToMany
    @JoinColumn(name = "contact_id")
    private List<Email> emails;
    @OneToMany
    @JoinColumn(name = "contact_id")
    private List<PhoneNumber> phoneNumbers;
}
