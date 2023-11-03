package by.academy.springboot.mapper;

import by.academy.springboot.dto.StreetTypeDTO;
import by.academy.springboot.model.entity.StreetType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StreetTypeMapper {
    StreetTypeMapper INSTANCE = Mappers.getMapper(StreetTypeMapper.class);
    StreetTypeDTO toDTO(StreetType model);
}
