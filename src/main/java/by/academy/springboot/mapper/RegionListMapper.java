package by.academy.springboot.mapper;

import by.academy.springboot.dto.RegionDTO;
import by.academy.springboot.model.entity.Region;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = RegionMapper.class)
public interface RegionListMapper {
    RegionListMapper INSTANCE = Mappers.getMapper(RegionListMapper.class);
    List<RegionDTO> toDTO(List<Region> models);
}
