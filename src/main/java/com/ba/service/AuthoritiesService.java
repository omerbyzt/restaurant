package com.ba.service;

import com.ba.converter.AuthoritiesConverter;
import com.ba.dto.AuthoritiesDTO;
import com.ba.entity.Authorities;
import com.ba.repository.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthoritiesService {

    @Autowired
    AuthoritiesRepository authoritiesRepository;

    public void loadAdmin(Authorities auth){
        authoritiesRepository.save(auth);
    }

    public List<AuthoritiesDTO> authoritiesList(){
        return AuthoritiesConverter.convertAuthListToAuthListDTO(authoritiesRepository.findAll());
    }

    public void addAuth(AuthoritiesDTO authDTO){
        authoritiesRepository.save(AuthoritiesConverter.convertAuthDTOToauth(authDTO));
    }

    public List<AuthoritiesDTO> deleteAuth(String username){
        authoritiesRepository.deleteById(username);
        return authoritiesList();
    }

    public List<AuthoritiesDTO> updateAuth (AuthoritiesDTO authDTO){
        authoritiesRepository.saveAndFlush(AuthoritiesConverter.convertAuthDTOToauth(authDTO));
        return authoritiesList();
    }
}
