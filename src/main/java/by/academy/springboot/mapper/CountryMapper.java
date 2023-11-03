package by.academy.springboot.mapper;

import by.academy.springboot.dto.CountryDTO;
import by.academy.springboot.model.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CountryMapper {
    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);
    CountryDTO toDTO(Country model);
}
