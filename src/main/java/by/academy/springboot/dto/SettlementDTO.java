package by.academy.springboot.dto;

import lombok.Data;

@Data
public class SettlementDTO {
    private int id;
    private int regionId;
    private int settlementTypeId;
    private String settlementName;
}
