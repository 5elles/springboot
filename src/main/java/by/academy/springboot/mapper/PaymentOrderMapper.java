package by.academy.springboot.mapper;

import by.academy.springboot.dto.PaymentOrderDTO;
import by.academy.springboot.model.entity.PaymentOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentOrderMapper {
    PaymentOrderMapper INSTANCE = Mappers.getMapper(PaymentOrderMapper.class);
    @Mapping(source = "model.fromAccount.accountNumber",
            target = "fromAccountNumber")
    @Mapping(source = "model.fromAccount.customer.id",
            target = "fromAccountCustomerId")
    @Mapping(source = "model.fromAccount.customer.person.firstName",
            target = "fromAccountCustomerFirstName")
    @Mapping(source = "model.fromAccount.customer.person.middleName",
            target = "fromAccountCustomerMiddleName")
    @Mapping(source = "model.fromAccount.customer.person.lastName",
            target = "fromAccountCustomerLastName")
    @Mapping(source = "model.fromAccount.customer.person.dateOfBirth",
            target = "fromAccountCustomerDateOfBirth")
    @Mapping(source = "model.fromAccount.customer.person.citizenIdNumber",
            target = "fromAccountCustomerCitizenIdNumber")
    @Mapping(source = "model.fromAccount.customer.person.passportNumber",
            target = "fromAccountCustomerPassportNumber")
    @Mapping(source = "model.toAccount.accountNumber",
            target = "toAccountNumber")
    @Mapping(source = "model.toAccount.customer.id",
            target = "toAccountCustomerId")
    @Mapping(source = "model.toAccount.customer.person.firstName",
            target = "toAccountCustomerFirstName")
    @Mapping(source = "model.toAccount.customer.person.middleName",
            target = "toAccountCustomerMiddleName")
    @Mapping(source = "model.toAccount.customer.person.lastName",
            target = "toAccountCustomerLastName")
    @Mapping(source = "model.toAccount.customer.person.dateOfBirth",
            target = "toAccountCustomerDateOfBirth")
    @Mapping(source = "model.toAccount.customer.person.citizenIdNumber",
            target = "toAccountCustomerCitizenIdNumber")
    @Mapping(source = "model.toAccount.customer.person.passportNumber",
            target = "toAccountCustomerPassportNumber")
    PaymentOrderDTO toDTO(PaymentOrder model);
}
