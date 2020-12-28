package com.ba.mapper;

import com.ba.dto.RoleDTO;
import com.ba.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
//    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Role toEntity(RoleDTO roleDTO);
    RoleDTO toDTO(Role role);
    List<Role> toList(List<RoleDTO> roleDTOList);
    List<RoleDTO> toDTOList(List<Role> roleList);
}
