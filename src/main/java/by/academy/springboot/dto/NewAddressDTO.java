package by.academy.springboot.dto;

import lombok.Data;

@Data
public class NewAddressDTO {
    private Integer id;
    private Integer settlementId;
    private Integer streetTypeId;
    private String streetName;
    private String houseNumber;
    private String apartmentNumber;
    private Integer personId;
}
