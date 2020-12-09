package com.ba.builder;

import com.ba.entity.Authorities;
import liquibase.pro.packaged.A;

public class AuthoritiesBuilder extends BuilderUserAuth{

    private String authority;

    public AuthoritiesBuilder username(String username){
        this.setUsername(username);
        return this;
    }

    public AuthoritiesBuilder authority(String authority){
        this.authority = authority;
        return this;
    }

    @Override
    public Authorities build() {
        Authorities authorities = new Authorities();

        authorities.setUsername(this.getUsername());
        authorities.setAuthority(this.authority);

        return authorities;
    }
}
