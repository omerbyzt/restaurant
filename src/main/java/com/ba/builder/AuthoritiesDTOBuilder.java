package com.ba.builder;

import com.ba.dto.AuthoritiesDTO;

public class AuthoritiesDTOBuilder extends BuilderUserAuth{

    private String authority;

    public AuthoritiesDTOBuilder username(String username){
        this.setUsername(username);
        return this;
    }

    public AuthoritiesDTOBuilder authority(String authority){
        this.authority = authority;
        return this;
    }

    @Override
    public AuthoritiesDTO build() {
        AuthoritiesDTO authoritiesDTO = new AuthoritiesDTO();

        authoritiesDTO.setUsername(this.getUsername());
        authoritiesDTO.setAuthority(this.authority);

        return authoritiesDTO;
    }
}
