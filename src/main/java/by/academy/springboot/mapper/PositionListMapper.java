package by.academy.springboot.mapper;

import by.academy.springboot.dto.PositionDTO;
import by.academy.springboot.model.entity.Position;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = PositionMapper.class)
public interface PositionListMapper {
    PositionListMapper INSTANCE = Mappers.getMapper(PositionListMapper.class);
    List<PositionDTO> toDTO(List<Position> models);
}
