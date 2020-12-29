package com.ba.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO implements Serializable {

    private Long id;
    @NotNull(message = "Role name cannot null...!")
    private String name;
    private boolean deleted;
}
