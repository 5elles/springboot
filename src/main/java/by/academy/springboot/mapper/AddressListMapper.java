package by.academy.springboot.mapper;

import by.academy.springboot.dto.AddressDTO;
import by.academy.springboot.model.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = AddressMapper.class)
public interface AddressListMapper {
    AddressListMapper INSTANCE = Mappers.getMapper(AddressListMapper.class);
    List<Address> toModelList(List<AddressDTO> dtoList);
    List<AddressDTO> toDTOList(List<Address> modelList);
}
