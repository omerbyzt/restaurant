package com.ba.controller;

import com.ba.model.Authorities;
import com.ba.model.Users;
import com.ba.service.AuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthoritiesController {

    @Autowired
    AuthoritiesService authoritiesService;

    @GetMapping("/loadadminauth")
    public void loadAdmin(){
        Authorities auth = new Authorities("admin","ROLE_ADMIN");
        authoritiesService.loadAdmin(auth);
    }

    @GetMapping("/listall")
    public List<Authorities> authoritiesList(){
        return authoritiesService.authoritiesList();
    }

    @PostMapping("/add")
    public void addAuth(@RequestBody Authorities auth){
        authoritiesService.addAuth(auth);
    }

    @DeleteMapping("/delete/{username}")
    public List<Authorities> deleteAuth(@PathVariable String username){
        return authoritiesService.deleteAuth(username);
    }

    @PutMapping("/update")
    public Authorities updateAuth(@RequestBody Authorities auth){
        return authoritiesService.updateAuth(auth);
    }
}
