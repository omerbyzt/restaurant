package com.ba.service;

import com.ba.dto.RoleDTO;
import com.ba.entity.Role;
import com.ba.exception.SystemException;
import com.ba.helper.UpdateHelper;
import com.ba.mapper.RoleMapper;
import com.ba.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    public List<RoleDTO> listRoles(){
        List<Role> role = roleRepository.findAll();
        if(role == null){
            throw new SystemException("Roles cannot be found...!");
        }
        return roleMapper.toDTOList(role);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String addRole(RoleDTO roleDTO){
        roleRepository.save(roleMapper.toEntity(roleDTO));
        return "Role Added";
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String deleteRole(Long id){
        roleRepository.deleteById(id);
        return "Role Deleted";
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String updateRole(RoleDTO roleDTO){
        Optional<Role> role = roleRepository.findById(roleDTO.getId());
        if(role == null){
            throw new SystemException("Role not found");
        }
        UpdateHelper.roleSetCheck(roleDTO, role);

        roleRepository.saveAndFlush(roleMapper.toEntity(roleDTO));
        return "Role Updated";
    }
}
