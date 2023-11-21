package by.academy.springboot.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailDTO {
    private Integer id;
    private String email;
    private Integer contactId;
    private Integer personId;
}
