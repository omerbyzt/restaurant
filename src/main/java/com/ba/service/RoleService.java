package com.ba.service;

import com.ba.converter.RoleConverter;
import com.ba.dto.RoleDTO;
import com.ba.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<RoleDTO> listRoles(){
        return RoleConverter.convertListToDTOList(roleRepository.findAll());
    }

    public String addRole(RoleDTO roleDTO){
        roleRepository.save(RoleConverter.convertDTOToEntity(roleDTO));
        return "Role Added";
    }

    public String deleteRole(Long id){
        roleRepository.deleteById(id);
        return "Role Deleted";
    }

    public String updateRole(RoleDTO roleDTO){
        roleRepository.saveAndFlush(RoleConverter.convertDTOToEntity(roleDTO));
        return "Role Updated";
    }
}
