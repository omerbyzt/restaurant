package com.ba.controller;

import com.ba.dto.RoleDTO;
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
        return roleService.addRole(roleDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRole(@PathVariable Long id){
        return roleService.deleteRole(id);
    }

    @PutMapping("/update")
    public String updateRole(@RequestBody RoleDTO roleDTO){
        return roleService.updateRole(roleDTO);
    }

}
