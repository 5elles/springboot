package by.academy.springboot.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class PhoneNumberDTO {
    private Integer id;
    private String phoneNumber;
    private Integer contactId;
    private Integer personId;
}
