package by.academy.springboot.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
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
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "clearence_level_id")
    private ClearanceLevel clearanceLevel;
}
