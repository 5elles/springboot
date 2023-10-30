package by.academy.springboot.mapper;

import by.academy.springboot.dto.PositionDTO;
import by.academy.springboot.model.entity.ClearanceLevel;
import by.academy.springboot.model.entity.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

class PositionMapperTest {

    @Test
    void shouldMapPositionToDTO_thenCorrect() {
        Position position = Position.builder()
                .id(1)
                .positionName("test")
                .salary(10.10)
                .build();
        PositionDTO dto = PositionMapper.INSTANCE.toDTO(position);
        assertNotNull(dto);
        assertEquals(position.getId(), dto.getId());
        assertEquals(position.getPositionName(), dto.getPositionName());
        assertEquals(position.getSalary(), dto.getSalary());
    }
}