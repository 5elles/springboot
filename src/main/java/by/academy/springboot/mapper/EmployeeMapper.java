package by.academy.springboot.mapper;

import by.academy.springboot.dto.EmployeeDTO;
import by.academy.springboot.model.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
        AddressListMapper.class,
        WageRateListMapper.class
})
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "employee.person.firstName", target = "firstName")
    @Mapping(source = "employee.person.middleName", target = "middleName")
    @Mapping(source = "employee.person.lastName", target = "lastName")
    @Mapping(source = "employee.person.dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "employee.person.citizenIdNumber", target = "citizenIdNumber")
    @Mapping(source = "employee.person.passportNumber", target = "passportNumber")
    @Mapping(source = "employee.person.addresses", target = "addresses")
    EmployeeDTO toDTO(Employee employee);
}
