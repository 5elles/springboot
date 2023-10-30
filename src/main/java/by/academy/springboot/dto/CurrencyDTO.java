package by.academy.springboot.dto;

import lombok.Data;

@Data
public class CurrencyDTO {
    private Integer id;
    private String currencyName;
    private String currencyAbbreviation;
    private String currencyCode;
    private Double currencyRate;
}
