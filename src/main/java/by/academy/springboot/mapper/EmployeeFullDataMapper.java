package by.academy.springboot.mapper;

import by.academy.springboot.dto.EmployeeFullDataDTO;
import by.academy.springboot.model.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
        AddressListMapper.class,
        EmailListMapper.class,
        PhoneNumberListMapper.class,
        WageRateFullDataListMapper.class
})
public interface EmployeeFullDataMapper {
    EmployeeFullDataMapper INSTANCE = Mappers.getMapper(EmployeeFullDataMapper.class);
    @Mapping(source = "employee.person.firstName", target = "firstName")
    @Mapping(source = "employee.person.middleName", target = "middleName")
    @Mapping(source = "employee.person.lastName", target = "lastName")
    @Mapping(source = "employee.person.dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "employee.person.citizenIdNumber", target = "citizenIdNumber")
    @Mapping(source = "employee.person.passportNumber", target = "passportNumber")
    @Mapping(source = "employee.person.addresses", target = "addresses")
    @Mapping(source = "contact.phoneNumbers", target = "phoneNumbers")
    @Mapping(source = "contact.emails", target = "emails")
    @Mapping(source = "employee.id", target = "id")
    @Mapping(source = "employee.person.id", target = "personId")
    EmployeeFullDataDTO toDTO(Employee employee,
                              Contact contact);
}
