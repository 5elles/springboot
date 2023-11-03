package by.academy.springboot.mapper;

import by.academy.springboot.dto.SettlementDTO;
import by.academy.springboot.dto.SettlementTypeDTO;
import by.academy.springboot.model.entity.Settlement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SettlementMapper {
    SettlementMapper INSTANCE = Mappers.getMapper(SettlementMapper.class);
    SettlementDTO toDTO(Settlement model);
}
