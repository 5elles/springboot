package by.academy.springboot.mapper;

import by.academy.springboot.dto.PaymentOrderDTO;
import by.academy.springboot.model.entity.PaymentOrder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PaymentOrderListMapper {
    PaymentOrderListMapper INSTANCE = Mappers.getMapper(PaymentOrderListMapper.class);
    List<PaymentOrderDTO> toDTOList(List<PaymentOrder> modelList);
}
