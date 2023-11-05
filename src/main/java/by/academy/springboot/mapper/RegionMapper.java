package by.academy.springboot.mapper;

import by.academy.springboot.dto.RegionDTO;
import by.academy.springboot.model.entity.Region;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegionMapper {
    RegionMapper INSTANCE = Mappers.getMapper(RegionMapper.class);
    @Mapping(source = "model.country.id", target = "countryId")
    RegionDTO toDTO(Region model);
}
