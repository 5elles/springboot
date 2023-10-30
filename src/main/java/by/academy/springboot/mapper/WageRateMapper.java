package by.academy.springboot.mapper;

import by.academy.springboot.dto.WageRateDTO;
import by.academy.springboot.model.entity.Position;
import by.academy.springboot.model.entity.WageRate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
@Mapper
public interface WageRateMapper {
    WageRateMapper INSTANCE = Mappers.getMapper(WageRateMapper.class);
    @Mapping(source = "model.position.positionName", target = "positionName")
    @Mapping(source = "model.position.salary", target = "positionSalary")
    WageRateDTO toDTO(WageRate model);
    WageRate toModel(WageRateDTO dto);
}
