package com.ba.controller;

import com.ba.model.Users;
import com.ba.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UsersController {

    @Autowired
    UsersService usersService;

    @GetMapping("/loadadminusers")
    public void loadAdmin(){
        Users users = new Users("admin","{noop}pass3",true);
        usersService.loadAdmin(users);
    }

    @GetMapping("/listall")
    public List<Users> listUsers(){
        return usersService.listUsers();
    }

    @PostMapping("/add")
    public void addUsers(@RequestBody Users users){
        usersService.addUsers(users);
    }

    @DeleteMapping("/delete/{username}")
    public List<Users> deleteUsers(@PathVariable String username){
        return usersService.deleteUsers(username);
    }

    @PutMapping("/update")
    public List<Users> updateUsers(@RequestBody Users users){
        return usersService.updateUsers(users);
    }
}
