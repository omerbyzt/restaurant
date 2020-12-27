package com.ba.mapper;

import com.ba.dto.UserDTO;
import com.ba.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-28T00:28:41+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    private final RoleMapper roleMapper = Mappers.getMapper( RoleMapper.class );

    @Override
    public User toEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setDeleted( userDTO.isDeleted() );
        user.setEmail( userDTO.getEmail() );
        user.setUsername( userDTO.getUsername() );
        user.setPassword( userDTO.getPassword() );
        if ( userDTO.getEnabled() != null ) {
            user.setEnabled( userDTO.getEnabled() );
        }
        user.setRoles( roleMapper.toList( userDTO.getRoles() ) );

        return user;
    }

    @Override
    public UserDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setEnabled( user.isEnabled() );
        userDTO.setRoles( roleMapper.toDTOList( user.getRoles() ) );
        userDTO.setDeleted( user.isDeleted() );

        return userDTO;
    }

    @Override
    public List<User> toList(List<UserDTO> userDTOList) {
        if ( userDTOList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( userDTOList.size() );
        for ( UserDTO userDTO : userDTOList ) {
            list.add( toEntity( userDTO ) );
        }

        return list;
    }

    @Override
    public List<UserDTO> toDTOList(List<User> userList) {
        if ( userList == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( userList.size() );
        for ( User user : userList ) {
            list.add( toDTO( user ) );
        }

        return list;
    }
}
