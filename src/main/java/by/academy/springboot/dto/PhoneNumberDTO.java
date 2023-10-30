package by.academy.springboot.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhoneNumberDTO {

    private Integer id;
    private String phoneNumber;
}
