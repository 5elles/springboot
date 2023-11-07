package by.academy.springboot.mapper;

import by.academy.springboot.dto.CurrencyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = CurrencyMapper.class)
public interface CurrencyListMapper {
    CurrencyListMapper INSTANCE = Mappers.getMapper(CurrencyListMapper.class);
    List<CurrencyDTO> toDTO(List<by.academy.springboot.model.entity.Currency> model);


}
