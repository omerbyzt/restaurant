package com.ba.dto;

import com.ba.entity.Role;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotNull(message = "User email cannot null...!")
    private String email;

    @NotNull(message = "User name cannot null...!")
    private String username;

    @NotNull(message = "User password cannot null...!")
    private String password;

    @NotNull(message = "User enabled cannot null...!")
    private Boolean enabled;

    @NotNull(message = "User roles cannot null...!")
    private List<RoleDTO> roles = new ArrayList<>();

    private boolean deleted;
}
