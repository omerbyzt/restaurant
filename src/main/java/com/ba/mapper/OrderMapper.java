package com.ba.mapper;

import com.ba.dto.OrderDTO;
import com.ba.entity.Orderr;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    List<Orderr> toList(List<OrderDTO> orderDTOList);
    List<OrderDTO> toDTOList(List<Orderr> orderList);
}
