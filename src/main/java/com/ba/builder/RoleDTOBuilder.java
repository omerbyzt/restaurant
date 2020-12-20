package com.ba.builder;

import com.ba.dto.RoleDTO;

public class RoleDTOBuilder extends Builder{

    private String name;

    public RoleDTOBuilder id(Long id){
        this.setId(id);
        return this;
    }

    public RoleDTOBuilder name(String name){
        this.name = name;
        return this;
    }

    @Override
    public RoleDTO build() {
        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId(this.getId());
        roleDTO.setName(this.name);

        return roleDTO;
    }
}
