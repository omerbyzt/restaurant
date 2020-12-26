package com.ba.mapper;

import com.ba.dto.UserDTO;
import com.ba.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {RoleMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDTO userDTO);
    UserDTO toDTO(User user);
    List<User> toList(List<UserDTO> userDTOList);
    List<UserDTO> toDTOList(List<User> userList);
}
