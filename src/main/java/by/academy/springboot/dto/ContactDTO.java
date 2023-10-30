package by.academy.springboot.dto;
import lombok.Data;
import java.util.List;

@Data
public class ContactDTO {
    private Integer id;
    private List<EmailDTO> emails;
    private List<PhoneNumberDTO> phoneNumbers;
}
