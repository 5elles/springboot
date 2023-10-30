package by.academy.springboot.mapper;

import by.academy.springboot.dto.WageRateFullDataDTO;
import by.academy.springboot.model.entity.WageRate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = WageRateFullDataMapper.class)
public interface WageRateFullDataListMapper {
    WageRateFullDataListMapper INSTANCE = Mappers.getMapper(WageRateFullDataListMapper.class);
    List<WageRateFullDataDTO> toDTOList(List<WageRate> modelList);
    List<WageRate> toModelList(List<WageRateFullDataDTO> dtoList);
}
