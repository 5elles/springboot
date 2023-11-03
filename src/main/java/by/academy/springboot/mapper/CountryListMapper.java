package by.academy.springboot.mapper;

import by.academy.springboot.dto.CountryDTO;
import by.academy.springboot.model.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = CountryMapper.class)
public interface CountryListMapper {
    CountryListMapper INSTANCE = Mappers.getMapper(CountryListMapper.class);
    List<CountryDTO> toDTO(List<Country> models);
}
