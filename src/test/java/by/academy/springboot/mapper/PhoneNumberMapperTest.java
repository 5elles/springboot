package by.academy.springboot.mapper;

import by.academy.springboot.dto.PhoneNumberDTO;
import by.academy.springboot.model.entity.PhoneNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberMapperTest {

    @Test
    void shouldMapPhoneNumberToDTO() {
        PhoneNumber number = PhoneNumber.builder()
                .phoneNumber("+3751234567890")
                .id(1)
                .build();
        PhoneNumberDTO dto = PhoneNumberMapper.INSTANCE.toDTO(number);
        assertNotNull(dto);
        assertEquals(number.getPhoneNumber(), dto.getPhoneNumber());
        assertEquals(number.getId(), dto.getId());
    }

    @Test
    void shouldMapDTOToPhoneNumber() {
        PhoneNumberDTO dto = PhoneNumberDTO.builder()
                .id(1)
                .phoneNumber("1111111111111111")
                .build();
        PhoneNumber number = PhoneNumberMapper.INSTANCE.toModel(dto);
        assertNotNull(number);
        assertEquals(dto.getId(), number.getId());
        assertEquals(dto.getPhoneNumber(), number.getPhoneNumber());
    }
}