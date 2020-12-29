package com.ba.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO implements Serializable {

    private Long id;

    @NotNull(message = "Category name cannot null...!")
    private String name;

    @NotNull(message = "Category description cannot null...!")
    private String description;

    private List<ProductDTO> products;

    @NotNull(message = "Category media cannot null...!")
    private MediaDTO mediaDTO;

    private boolean deleted;
}
