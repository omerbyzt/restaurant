//package com.ba.controller;
//
//import com.ba.dto.UsersDTO;
//import com.ba.entity.Users;
//import com.ba.service.UsersService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/users")
//@CrossOrigin(origins = "*")
//public class UsersController {
//
//    @Autowired
//    UsersService usersService;
//
//    @GetMapping("/loadadminusers")
//    public void loadAdmin(){
//        Users users = new Users("admin","{noop}pass3",true);
//        usersService.loadAdmin(users);
//    }
//
//    @GetMapping("/listall")
//    public List<UsersDTO> listUsers(){
//        return usersService.listUsers();
//    }
//
//    @PostMapping("/add")
//    public String addUsers(@RequestBody UsersDTO usersDTO){
//        usersService.addUsers(usersDTO);
//        return "User Added";
//    }
//
//    @DeleteMapping("/delete/{username}")
//    public String deleteUsers(@PathVariable String username){
//        return usersService.deleteUsers(username);
//    }
//
//    @PutMapping("/update")
//    public UsersDTO updateUsers(@RequestBody UsersDTO usersDTO){
//        return usersService.updateUsers(usersDTO);
//    }
//}
