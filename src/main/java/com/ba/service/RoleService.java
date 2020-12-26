package com.ba.service;

import com.ba.dto.RoleDTO;
import com.ba.entity.Role;
import com.ba.exception.SystemException;
import com.ba.helper.UpdateHelper;
import com.ba.mapper.RoleMapper;
import com.ba.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<RoleDTO> listRoles(){
        List<Role> role = roleRepository.findAll();
        if(role.isEmpty()){
            throw new SystemException("Roles cannot be found...!");
        }
        return RoleMapper.INSTANCE.toDTOList(role);
    }

    public String addRole(RoleDTO roleDTO){
        roleRepository.save(RoleMapper.INSTANCE.toEntity(roleDTO));
        return "Role Added";
    }

    public String deleteRole(Long id){
        roleRepository.deleteById(id);
        return "Role Deleted";
    }

    public String updateRole(RoleDTO roleDTO){
        Optional<Role> role = roleRepository.findById(roleDTO.getId());
        if(role.isEmpty()){
            throw new SystemException("Role not found");
        }
        UpdateHelper.roleSetCheck(roleDTO, role);

        roleRepository.saveAndFlush(RoleMapper.INSTANCE.toEntity(roleDTO));
        return "Role Updated";
    }
}
