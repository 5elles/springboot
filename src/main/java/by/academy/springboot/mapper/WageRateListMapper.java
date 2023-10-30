package by.academy.springboot.mapper;

import by.academy.springboot.dto.WageRateDTO;
import by.academy.springboot.model.entity.WageRate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WageRateListMapper {
    WageRateListMapper INSTANCE = Mappers.getMapper(WageRateListMapper.class);
    List<WageRateDTO> toDTOList(List<WageRate> modelList);
    List<WageRate> toModelList(List<WageRateDTO> dtoList);
}
