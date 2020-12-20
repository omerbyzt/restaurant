package com.ba.builder;

import com.ba.dto.RoleDTO;
import com.ba.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserDTOBuilder extends Builder {

    private String email;
    private String username;
    private String password;
    private Boolean enabled;
    private List<RoleDTO> roles = new ArrayList<>();

    public UserDTOBuilder id(Long id) {
        this.setId(id);
        return this;
    }

    public UserDTOBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserDTOBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserDTOBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserDTOBuilder enabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public UserDTOBuilder roles(List<RoleDTO> roles) {
        this.roles = roles;
        return this;
    }

    @Override
    public UserDTO build() {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(this.getId());
        userDTO.setEmail(this.email);
        userDTO.setUsername(this.username);
        userDTO.setPassword(this.password);
        userDTO.setEnabled(this.enabled);
        userDTO.setRoles(this.roles);

        return userDTO;
    }
}
