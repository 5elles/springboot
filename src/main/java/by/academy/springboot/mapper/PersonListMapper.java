package by.academy.springboot.mapper;

import by.academy.springboot.dto.PersonDTO;
import by.academy.springboot.model.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = PersonMapper.class)
public interface PersonListMapper {
    PersonListMapper INSTANCE = Mappers.getMapper(PersonListMapper.class);
    List<PersonDTO> toDTO(List<Person> models);
}
