package com.ba.mapper;

import com.ba.dto.OrderDTO;
import com.ba.dto.OrderItemDTO;
import com.ba.entity.Order;
import com.ba.entity.OrderItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderDTO orderDTO);
    OrderDTO toDTO(Order order);
    List<Order> toEntityList(List<OrderDTO> orderDTOList);
    List<OrderDTO> toDTOList(List<Order> orderList);
}
