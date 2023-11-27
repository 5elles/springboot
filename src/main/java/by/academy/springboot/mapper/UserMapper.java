package by.academy.springboot.mapper;

import by.academy.springboot.dto.UserDTO;
import by.academy.springboot.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public  interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "model.person.id", target = "personId")
    UserDTO toDTO(User model);

    @Mapping(source = "dto.personId", target = "person.id")
    User toModel(UserDTO dto);
}
