package by.academy.springboot.mapper;

import by.academy.springboot.dto.AddressDTO;
import by.academy.springboot.model.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(source = "address.settlement.region.country.countryName",
            target = "countryName")
    @Mapping(source = "address.settlement.region.regionName",
            target = "regionName")
    @Mapping(source = "address.settlement.settlementType.settlementTypeName",
            target = "settlementTypeName")
    @Mapping(source = "address.settlement.settlementType.shortName",
            target = "settlementTypeShortName")
    @Mapping(source = "address.settlement.settlementName",
            target = "settlementName")
    @Mapping(source = "address.streetType.shortName", target = "streetTypeShortName")
    AddressDTO toDTO(Address address);

}

