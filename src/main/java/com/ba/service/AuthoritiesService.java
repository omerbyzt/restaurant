package com.ba.service;

import com.ba.model.Authorities;
import com.ba.model.Users;
import com.ba.repository.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthoritiesService {

    @Autowired
    AuthoritiesRepository authoritiesRepository;

    public void loadAdmin(Authorities auth){
        authoritiesRepository.save(auth);
    }

    public List<Authorities> authoritiesList(){
        return authoritiesRepository.findAll();
    }

    public void addAuth(Authorities auth){
        authoritiesRepository.save(auth);
    }

    public List<Authorities> deleteAuth(String username){
        authoritiesRepository.deleteById(username);
        return authoritiesList();
    }

    public Authorities updateAuth (Authorities auth){
        return authoritiesRepository.saveAndFlush(auth);

    }
}
