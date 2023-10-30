package by.academy.springboot.mapper;

import by.academy.springboot.dto.EmailDTO;
import by.academy.springboot.model.entity.Email;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmailMapper {
    EmailMapper INSTANCE = Mappers.getMapper(EmailMapper.class);
    Email toModel(EmailDTO dto);
    EmailDTO toDTO(Email model);
}
