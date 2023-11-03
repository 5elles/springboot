package by.academy.springboot.mapper;

import by.academy.springboot.dto.SettlementDTO;
import by.academy.springboot.model.entity.Settlement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = SettlementMapper.class)
public interface SettlementListMapper {
    SettlementListMapper INSTANCE = Mappers.getMapper(SettlementListMapper.class);
    List<SettlementDTO> toDTO(List<Settlement> models);
}
