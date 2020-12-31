package com.ba.mapper;

import com.ba.dto.OrderItemDTO;
import com.ba.entity.OrderItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemDTO toDtO(OrderItem orderItem);
    OrderItem toEntity(OrderItemDTO orderItemDTO);
    List<OrderItem> toEntityList(List<OrderItemDTO> orderItemDTO);
    List<OrderItemDTO> toDTOList(List<OrderItem> orderItems);
}
