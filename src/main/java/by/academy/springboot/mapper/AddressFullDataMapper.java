package by.academy.springboot.mapper;

import by.academy.springboot.dto.*;
import by.academy.springboot.model.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AddressFullDataMapper {
    AddressFullDataMapper INSTANCE = Mappers.getMapper(AddressFullDataMapper.class);

    AddressFullDataDTO toDTO(
            List<Country> countries,
            List<Region> regions,
            List<SettlementType> settlementTypes,
            List<Settlement> settlements,
            List<StreetType> streetTypes
    );
}
