package com.ba.converter;

import com.ba.dto.AuthoritiesDTO;
import com.ba.entity.Authorities;

import java.util.ArrayList;
import java.util.List;

public class AuthoritiesConverter {

    public static List<AuthoritiesDTO> convertAuthListToAuthListDTO(List<Authorities> authList){
        List<AuthoritiesDTO> authListDTO = new ArrayList<>();

        for(Authorities authListItem : authList){
            AuthoritiesDTO authDTO = new AuthoritiesDTO();

            authDTO.setAuthority(authListItem.getAuthority());
            authDTO.setUsername(authListItem.getUsername());

            authListDTO.add(authDTO);
        }

        return authListDTO;
    }

    public static Authorities convertAuthDTOToauth(AuthoritiesDTO authDTO){
        Authorities auth = new Authorities();
        auth.setAuthority(authDTO.getAuthority());
        auth.setUsername(authDTO.getUsername());
        return auth;
    }
}
