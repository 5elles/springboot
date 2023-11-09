package by.academy.springboot.dto;

import lombok.Data;

import java.util.List;

@Data
public class PositionDTO {
    private Integer id;
    private String positionName;
    private Double salary;
    private List<WageRateFullDataDTO> wageRates;
}
