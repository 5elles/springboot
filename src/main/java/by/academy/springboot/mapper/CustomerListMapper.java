package by.academy.springboot.mapper;

import by.academy.springboot.dto.CustomerDTO;
import by.academy.springboot.model.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerListMapper {
    CustomerListMapper INSTANCE = Mappers.getMapper(CustomerListMapper.class);
    List<CustomerDTO> toDTOList(List<Customer> modelList);
    List<Customer> toModelList(List<CustomerDTO> dtoList);
}
