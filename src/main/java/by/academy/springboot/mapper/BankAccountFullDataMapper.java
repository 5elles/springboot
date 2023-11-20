package by.academy.springboot.mapper;

import by.academy.springboot.dto.BankAccountFullDataDTO;
import by.academy.springboot.dto.PaymentOrderDTO;
import by.academy.springboot.model.entity.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = PaymentOrderMapper.class)
public interface BankAccountFullDataMapper {
    BankAccountFullDataMapper INSTANCE = Mappers.getMapper(BankAccountFullDataMapper.class);

    @Mapping(source = "bankAccount.customer.person.firstName",
            target = "firstName")
    @Mapping(source = "bankAccount.customer.person.middleName",
            target = "middleName")
    @Mapping(source = "bankAccount.customer.person.lastName",
            target = "lastName")
    @Mapping(source = "bankAccount.customer.person.dateOfBirth",
            target = "dateOfBirth")
    @Mapping(source = "bankAccount.customer.person.citizenIdNumber",
            target = "citizenIdNumber")
    @Mapping(source = "bankAccount.customer.person.passportNumber",
            target = "passportNumber")
    @Mapping(source = "bankAccount.currency.currencyAbbreviation",
            target = "currencyAbbreviation")
    @Mapping(source = "bankAccount.currency.currencyRate",
            target = "currencyRate")
    BankAccountFullDataDTO modelsToDTO(
            BankAccount bankAccount,
            List<PaymentOrderDTO> allPayments,
            List<PaymentOrderDTO> outgoingPayments,
            List<PaymentOrderDTO> incomingPayments
            );
}
