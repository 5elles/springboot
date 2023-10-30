package by.academy.springboot.mapper;

import by.academy.springboot.dto.EmailDTO;
import by.academy.springboot.model.entity.Email;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmailListMapper {
    EmailListMapper INSTANCE = Mappers.getMapper(EmailListMapper.class);
    List<Email> toModelList(List<EmailDTO> dtoList);
    List<EmailDTO> toDTOList(List<Email> modelList);
}
