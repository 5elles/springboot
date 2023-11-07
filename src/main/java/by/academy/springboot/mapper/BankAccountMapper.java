package by.academy.springboot.mapper;

import by.academy.springboot.dto.BankAccountDTO;
import by.academy.springboot.model.entity.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface BankAccountMapper {

    BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);

    @Mapping(source = "model.currency.currencyAbbreviation", target = "currencyAbbreviation")
    @Mapping(source = "model.currency.currencyRate", target = "currencyRate")
    @Mapping(source = "model.customer.id", target = "customerID")
    BankAccountDTO modelToDTO(BankAccount model);

    @Mapping(source = "dto.currencyID", target = "currency.id")
    @Mapping(source = "dto.customerID", target = "customer.id")
    BankAccount dtoToModel(BankAccountDTO dto);
}
