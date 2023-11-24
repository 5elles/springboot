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
    @Mapping(source = "wageRate.employee.id", target = "employeeId")
    @Mapping(source = "wageRate.position.id", target = "positionId")
    @Mapping(source = "wageRate.position.role", target = "role")
    WageRateFullDataDTO toDTO(WageRate wageRate);
    @Mapping(source = "dto.positionName", target = "position.positionName")
    @Mapping(source = "dto.positionSalary", target = "position.salary")
    @Mapping(source = "dto.employeeId", target = "employee.id")
    @Mapping(source = "dto.positionId", target = "position.id")
    @Mapping(source = "dto.role", target = "position.role")
    WageRate toModel(WageRateFullDataDTO dto);
}
