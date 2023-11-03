package by.academy.springboot.mapper;

import by.academy.springboot.dto.SettlementTypeDTO;
import by.academy.springboot.model.entity.SettlementType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SettlementTypeMapper {
    SettlementTypeMapper INSTANCE = Mappers.getMapper(SettlementTypeMapper.class);
    SettlementTypeDTO toDTO(SettlementType model);
}
