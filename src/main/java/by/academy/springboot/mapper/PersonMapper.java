package by.academy.springboot.mapper;

import by.academy.springboot.dto.PersonDTO;
import by.academy.springboot.model.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AddressListMapper.class)
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
    PersonDTO toDTO(Person person);
    Person toModel(PersonDTO personDTO);
}
