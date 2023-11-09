package by.academy.springboot.mapper;

import by.academy.springboot.dto.EmployeeDTO;
import by.academy.springboot.model.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = EmployeeMapper.class)
public interface EmployeeListMapper {
    EmployeeListMapper INSTANCE = Mappers.getMapper(EmployeeListMapper.class);
    List<EmployeeDTO> toDTO(List<Employee> models);
}
