package by.academy.springboot.dto;

import by.academy.springboot.model.entity.Position;
import lombok.Data;

import java.time.LocalDate;

@Data
public class WageRateDTO {
    private Integer id;
    private Position position;
    private String positionName;
    private Double positionSalary;
    private LocalDate startDate;
    private LocalDate finishDate;
    private Double rate;
}
