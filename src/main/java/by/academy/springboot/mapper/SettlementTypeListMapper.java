package by.academy.springboot.mapper;

import by.academy.springboot.dto.SettlementTypeDTO;
import by.academy.springboot.model.entity.SettlementType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = SettlementTypeMapper.class)
public interface SettlementTypeListMapper {
    SettlementTypeListMapper INSTANCE = Mappers.getMapper(SettlementTypeListMapper.class);
    List<SettlementTypeDTO> toDTO(List<SettlementType> models);
}
