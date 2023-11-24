package by.academy.springboot.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WageRateFullDataDTO {
    private Integer id;
    private String positionName;
    private Integer positionId;
    private Double positionSalary;
    private LocalDate startDate;
    private LocalDate finishDate;
    private Double rate;
    private Integer employeeId;
    private String role;
}
