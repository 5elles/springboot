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
        AddressListMapper.class,
        PersonMapper.class
})
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "customer.person.firstName", target = "firstName")
    @Mapping(source = "customer.person.middleName", target = "middleName")
    @Mapping(source = "customer.person.lastName", target = "lastName")
    @Mapping(source = "customer.person.dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "customer.person.citizenIdNumber", target = "citizenIdNumber")
    @Mapping(source = "customer.person.passportNumber", target = "passportNumber")
    @Mapping(source = "customer.person.addresses", target = "addresses")
    CustomerDTO modelToDTO(Customer customer);
    Customer dtoToModel(CustomerDTO dto);
}


