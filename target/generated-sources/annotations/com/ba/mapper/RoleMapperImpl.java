package com.ba.mapper;

import com.ba.dto.RoleDTO;
import com.ba.entity.Role;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-31T02:24:36+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role toEntity(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleDTO.getId() );
        role.setDeleted( roleDTO.isDeleted() );
        role.setName( roleDTO.getName() );

        return role;
    }

    @Override
    public RoleDTO toDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId( role.getId() );
        roleDTO.setName( role.getName() );
        roleDTO.setDeleted( role.isDeleted() );

        return roleDTO;
    }

    @Override
    public List<Role> toList(List<RoleDTO> roleDTOList) {
        if ( roleDTOList == null ) {
            return null;
        }

        List<Role> list = new ArrayList<Role>( roleDTOList.size() );
        for ( RoleDTO roleDTO : roleDTOList ) {
            list.add( toEntity( roleDTO ) );
        }

        return list;
    }

    @Override
    public List<RoleDTO> toDTOList(List<Role> roleList) {
        if ( roleList == null ) {
            return null;
        }

        List<RoleDTO> list = new ArrayList<RoleDTO>( roleList.size() );
        for ( Role role : roleList ) {
            list.add( toDTO( role ) );
        }

        return list;
    }
}
