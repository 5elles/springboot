package by.academy.springboot.mapper;

import by.academy.springboot.dto.CurrencyDTO;
import by.academy.springboot.model.entity.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CurrencyMapper {
    CurrencyMapper INSTANCE = Mappers.getMapper(CurrencyMapper.class);
    CurrencyDTO toDTO(Currency currency);
}
