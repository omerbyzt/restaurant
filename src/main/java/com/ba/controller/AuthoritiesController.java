package com.ba.controller;

import com.ba.dto.AuthoritiesDTO;
import com.ba.entity.Authorities;
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
    public List<AuthoritiesDTO> authoritiesList(){
        return authoritiesService.authoritiesList();
    }

    @PostMapping("/add")
    public void addAuth(@RequestBody AuthoritiesDTO authDTO){
        authoritiesService.addAuth(authDTO);
    }

    @DeleteMapping("/delete/{username}")
    public List<AuthoritiesDTO> deleteAuth(@PathVariable String username){
        return authoritiesService.deleteAuth(username);
    }

    @PutMapping("/update")
    public List<AuthoritiesDTO> updateAuth(@RequestBody AuthoritiesDTO auth){
        return authoritiesService.updateAuth(auth);
    }
}
