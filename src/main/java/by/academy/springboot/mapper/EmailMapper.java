package by.academy.springboot.mapper;

import by.academy.springboot.dto.EmailDTO;
import by.academy.springboot.model.entity.Email;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmailMapper {
    EmailMapper INSTANCE = Mappers.getMapper(EmailMapper.class);
    @Mapping(source = "dto.contactId", target = "contact.id")
    Email toModel(EmailDTO dto);
    @Mapping(source = "model.contact.id", target = "contactId")
    EmailDTO toDTO(Email model);
}
