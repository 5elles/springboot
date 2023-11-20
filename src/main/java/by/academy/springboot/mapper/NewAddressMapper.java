package by.academy.springboot.mapper;

import by.academy.springboot.dto.NewAddressDTO;
import by.academy.springboot.model.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewAddressMapper {
    NewAddressMapper INSTANCE = Mappers.getMapper(NewAddressMapper.class);
    @Mapping(source = "dto.settlementId", target = "settlement.id")
    @Mapping(source = "dto.streetTypeId", target = "streetType.id")
    @Mapping(source = "dto.personId", target = "person.id")
    Address toModel(NewAddressDTO dto);
}
