package by.academy.springboot.mapper;

import by.academy.springboot.dto.SettlementDTO;
import by.academy.springboot.model.entity.Settlement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SettlementMapper {
    SettlementMapper INSTANCE = Mappers.getMapper(SettlementMapper.class);
    @Mapping(source = "model.region.id", target = "regionId")
    @Mapping(source = "model.settlementType.id", target = "settlementTypeId")
    SettlementDTO toDTO(Settlement model);
}
