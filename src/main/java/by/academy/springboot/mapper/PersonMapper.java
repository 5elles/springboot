package by.academy.springboot.mapper;

import by.academy.springboot.dto.PersonDTO;
import by.academy.springboot.model.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AddressListMapper.class)
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
    @Mapping(source = "person.employee.id", target = "employeeId")
    @Mapping(source = "person.customer.id", target = "customerId")
    @Mapping(source = "person.user.roles", target = "roles")
    PersonDTO toDTO(Person person);
    Person toModel(PersonDTO personDTO);
}
