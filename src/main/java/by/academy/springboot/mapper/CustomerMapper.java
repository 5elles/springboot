package by.academy.springboot.mapper;

import by.academy.springboot.dto.CustomerDTO;
import by.academy.springboot.model.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

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
    @Mapping(source = "customer.person.id", target = "personId")
    CustomerDTO modelToDTO(Customer customer);

    @Mapping(source = "dto.personId", target = "person.id")
    Customer toModel(CustomerDTO dto);
}


