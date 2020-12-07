package com.ba.controller;

import com.ba.unused.User;
import com.ba.unused.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/listall")
    public List<User> listAllUsers(){
        return userService.listAllUsers();
    }

    @PostMapping("/add")
    public List<User> addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public List<User> deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }
}
