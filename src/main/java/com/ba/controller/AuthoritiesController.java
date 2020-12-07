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
    public String addAuth(@RequestBody AuthoritiesDTO authDTO){
        authoritiesService.addAuth(authDTO);
        return "Auth Added";
    }

    @DeleteMapping("/delete/{username}")
    public String deleteAuth(@PathVariable String username){
        authoritiesService.deleteAuth(username);
        return "Auth Deleted";
    }

    @PutMapping("/update")
    public AuthoritiesDTO updateAuth(@RequestBody AuthoritiesDTO auth){
        return authoritiesService.updateAuth(auth);
    }
}
