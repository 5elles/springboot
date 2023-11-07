package by.academy.springboot.dto;

import lombok.Data;

@Data
public class EmailDTO {
    private Integer id;
    private String email;
    private Integer contactId;
    private Integer personId;
}
