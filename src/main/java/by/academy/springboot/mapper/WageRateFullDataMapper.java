package by.academy.springboot.mapper;

import by.academy.springboot.dto.WageRateFullDataDTO;
import by.academy.springboot.model.entity.WageRate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = PositionMapper.class)
public interface WageRateFullDataMapper {
    WageRateFullDataMapper INSTANCE = Mappers.getMapper(WageRateFullDataMapper.class);
    @Mapping(source = "wageRate.position.positionName", target = "positionName")
    @Mapping(source = "wageRate.position.salary", target = "positionSalary")
    WageRateFullDataDTO toDTO(WageRate wageRate);
    WageRate toModel(WageRateFullDataDTO dto);
}
