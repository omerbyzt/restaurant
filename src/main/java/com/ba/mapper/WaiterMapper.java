package com.ba.mapper;

import com.ba.dto.WaiterDTO;
import com.ba.entity.Waiter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WaiterMapper {
    WaiterMapper INSTANCE = Mappers.getMapper(WaiterMapper.class);

    @Mapping(source = "mediaDTO" , target = "media")
    Waiter toEntity(WaiterDTO waiterDTO);

    @Mapping(source = "media" , target = "mediaDTO")
    WaiterDTO toDTO(Waiter waiter);

    List<Waiter> toList(List<WaiterDTO> waiterDTOList);

    List<WaiterDTO> toDTOList(List<Waiter> waiterList);
}
