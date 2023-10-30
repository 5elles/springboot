package by.academy.springboot.mapper;

import by.academy.springboot.dto.AddressDTO;
import by.academy.springboot.dto.BankAccountDTO;
import by.academy.springboot.dto.ContactDTO;
import by.academy.springboot.dto.CustomerDTO;
import by.academy.springboot.model.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

@Mapper(uses = {
        BankAccountListMapper.class,
        AddressListMapper.class
})
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "model.person.firstName", target = "firstName")
    @Mapping(source = "model.person.middleName", target = "middleName")
    @Mapping(source = "model.person.lastName", target = "lastName")
    @Mapping(source = "model.person.dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "model.person.citizenIdNumber", target = "citizenIdNumber")
    @Mapping(source = "model.person.passportNumber", target = "passportNumber")
    @Mapping(source = "model.person.addresses", target = "addresses")
    CustomerDTO modelToDTO(Customer model);
    Customer dtoToModel(CustomerDTO dto);
}


