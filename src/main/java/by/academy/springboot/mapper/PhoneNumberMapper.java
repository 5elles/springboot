package by.academy.springboot.mapper;

import by.academy.springboot.dto.PhoneNumberDTO;
import by.academy.springboot.model.entity.PhoneNumber;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhoneNumberMapper {
    PhoneNumberMapper INSTANCE = Mappers.getMapper(PhoneNumberMapper.class);
    PhoneNumberDTO toDTO(PhoneNumber model);
    PhoneNumber toModel(PhoneNumberDTO dto);
}
