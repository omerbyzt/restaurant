package com.ba.controller;

import com.ba.dto.UserDTO;
import com.ba.entity.Role;
import com.ba.entity.User;
import com.ba.exception.BusinessRuleException;
import com.ba.repository.RoleRepository;
import com.ba.repository.UserRepository;
import com.ba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<UserDTO> listUsers(){
        return userService.listUsers();
    }

    @PostMapping("/add")
    public String addUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @PutMapping("/update")
    public String updateUser(@Valid @RequestBody UserDTO userDTO){
        if(userDTO.getId() == null){
            throw new BusinessRuleException("User id cannot empty...!");
        }
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        if(id == null){
            throw new BusinessRuleException("Id cannot be empty...!");
        }
        return userService.deleteUser(id);
    }
}
