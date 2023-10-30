package by.academy.springboot.mapper;

import by.academy.springboot.dto.BankAccountDTO;
import by.academy.springboot.model.entity.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = BankAccountMapper.class)
public interface BankAccountListMapper {
    BankAccountListMapper INSTANCE = Mappers.getMapper(BankAccountListMapper.class);
    List<BankAccount> toModelList(List<BankAccountDTO> dtoList);
    List<BankAccount> toDTOList(List<BankAccount> modelList);
}
