package by.academy.springboot.mapper;

import by.academy.springboot.config.SecurityConfig;
import by.academy.springboot.dto.UserDTO;
import by.academy.springboot.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.beans.Encoder;

@Mapper
public  interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "model.person.id", target = "personId")
    UserDTO toDTO(User model);

    @Mapping(source = "dto.personId", target = "person.id")
    User toModel(UserDTO dto);
}
