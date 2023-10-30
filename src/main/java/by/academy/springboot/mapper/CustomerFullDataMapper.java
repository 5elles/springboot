package by.academy.springboot.mapper;

import by.academy.springboot.dto.CustomerFullDataDTO;
import by.academy.springboot.model.entity.Contact;
import by.academy.springboot.model.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
        BankAccountListMapper.class,
        AddressListMapper.class,
        EmailListMapper.class,
        PhoneNumberListMapper.class
})
public interface CustomerFullDataMapper {
    CustomerFullDataMapper INSTANCE = Mappers.getMapper(CustomerFullDataMapper.class);

    @Mapping(source = "customer.id", target = "id")
    @Mapping(source = "customer.person.firstName", target = "firstName")
    @Mapping(source = "customer.person.middleName", target = "middleName")
    @Mapping(source = "customer.person.lastName", target = "lastName")
    @Mapping(source = "customer.person.dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "customer.person.citizenIdNumber", target = "citizenIdNumber")
    @Mapping(source = "customer.person.passportNumber", target = "passportNumber")
    @Mapping(source = "customer.person.addresses", target = "addresses")
    @Mapping(source = "contact.emails", target = "emails")
    @Mapping(source = "contact.phoneNumbers", target = "phoneNumbers")
    CustomerFullDataDTO modelsToDTO(Customer customer, Contact contact);
}
