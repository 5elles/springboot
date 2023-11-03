package by.academy.springboot.model.repository;

import by.academy.springboot.dto.StreetTypeDTO;
import by.academy.springboot.mapper.StreetTypeMapper;
import by.academy.springboot.model.entity.StreetType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = StreetTypeMapper.class)
public interface StreetTypeListMapper {
    StreetTypeListMapper INSTANCE = Mappers.getMapper(StreetTypeListMapper.class);
    List<StreetTypeDTO> toDTO(List<StreetType> models);
}
