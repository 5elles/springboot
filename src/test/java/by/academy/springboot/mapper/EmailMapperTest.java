package by.academy.springboot.mapper;

import by.academy.springboot.dto.EmailDTO;
import by.academy.springboot.model.entity.Email;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailMapperTest {

    @Test
    void shouldMapEmailToDTO() {
        Email email = Email.builder()
                .email("test@email.by")
                .id(1)
                .build();
        EmailDTO emailDTO = EmailMapper.INSTANCE.toDTO(email);
        assertNotNull(emailDTO);
        assertEquals(email.getEmail(), emailDTO.getEmail());
        assertEquals(email.getId(), emailDTO.getId());
    }
}