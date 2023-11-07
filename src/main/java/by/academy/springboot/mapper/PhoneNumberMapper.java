package by.academy.springboot.mapper;

import by.academy.springboot.dto.PhoneNumberDTO;
import by.academy.springboot.model.entity.PhoneNumber;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhoneNumberMapper {
    PhoneNumberMapper INSTANCE = Mappers.getMapper(PhoneNumberMapper.class);
    @Mapping(source = "model.contact.id", target = "contactId")
    PhoneNumberDTO toDTO(PhoneNumber model);
    @Mapping(source = "dto.contactId", target = "contact.id")
    PhoneNumber toModel(PhoneNumberDTO dto);
}
