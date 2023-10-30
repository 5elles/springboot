package by.academy.springboot.mapper;

import by.academy.springboot.dto.PhoneNumberDTO;
import by.academy.springboot.model.entity.PhoneNumber;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PhoneNumberListMapper {
    PhoneNumberListMapper INSTANCE = Mappers.getMapper(PhoneNumberListMapper.class);
    List<PhoneNumber> toModelList(List<PhoneNumberDTO> dtoList);
    List<PhoneNumberDTO> toDTOList(List<PhoneNumber> modelList);
}
