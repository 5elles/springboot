package by.academy.springboot.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private Integer id;
    private String countryName;
    private String regionName;
    private String settlementTypeName;
    private String settlementTypeShortName;
    private String settlementName;
    private String streetTypeShortName;
    private String streetName;
    private String houseNumber;
    private String apartmentNumber;
    private int personId;
}
