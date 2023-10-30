package by.academy.springboot.mapper;

import by.academy.springboot.dto.PositionDTO;
import by.academy.springboot.model.entity.Position;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PositionMapper {
    PositionMapper INSTANCE = Mappers.getMapper(PositionMapper.class);
    PositionDTO toDTO(Position model);
}
