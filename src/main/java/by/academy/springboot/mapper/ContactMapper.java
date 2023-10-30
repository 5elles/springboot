package by.academy.springboot.mapper;

import by.academy.springboot.dto.ContactDTO;
import by.academy.springboot.model.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {
        EmailListMapper.class,
        PhoneNumberListMapper.class
})
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);
    ContactDTO toDTO(Contact model);
}
