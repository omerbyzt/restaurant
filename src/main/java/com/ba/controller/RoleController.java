package com.ba.controller;

import com.ba.dto.RoleDTO;
import com.ba.exception.BusinessRuleException;
import com.ba.helper.InternationalizationHelper;
import com.ba.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

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
    public String deleteRole(@PathVariable Long id,@RequestHeader("Accept-Language") String locale){
        if(id <= 0){
//            throw new BusinessRuleException("Id cannot be empty...!");
            throw new BusinessRuleException(InternationalizationHelper.messageSource().getMessage("id.error",null,new Locale(locale)));
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
