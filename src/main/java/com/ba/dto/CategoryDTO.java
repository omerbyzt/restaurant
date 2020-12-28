package com.ba.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private List<ProductDTO> products;
    private MediaDTO mediaDTO;
    private boolean deleted;
}
