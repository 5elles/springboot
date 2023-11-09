package by.academy.springboot.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "position")
public class Position {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "position_name")
    private String positionName;
    @Column(name = "salary")
    private Double salary;
    @OneToMany
    @JoinColumn(name = "position_id")
    private List<WageRate> wageRates;
}
