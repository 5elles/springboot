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
@Table(name = "currency")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "currency_name")
    private String currencyName;
    @Column(name = "currency_abbreviation")
    private String currencyAbbreviation;
    @Column(name = "currency_code")
    private String currencyCode;
    @Column(name = "currency_rate")
    private Double currencyRate;
}
