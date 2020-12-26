package com.ba.controller;

import com.ba.dto.RoleDTO;
import com.ba.exception.BusinessRuleException;
import com.ba.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public List<RoleDTO> listRoles(){
        return roleService.listRoles();
    }

    @PostMapping("/add")
    public String addRole(@RequestBody RoleDTO roleDTO){
        if(roleDTO == null){
            throw new BusinessRuleException("Role cannot be empty...!");
        }
        return roleService.addRole(roleDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRole(@PathVariable Long id){
        if(id == null){
            throw new BusinessRuleException("Id cannot be empty...!");
        }
        return roleService.deleteRole(id);
    }

    @PutMapping("/update")
    public String updateRole(@RequestBody RoleDTO roleDTO){
        if(roleDTO == null){
            throw new BusinessRuleException("Role cannot be empty...!");
        }
        return roleService.updateRole(roleDTO);
    }
}
