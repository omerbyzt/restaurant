package com.ba.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {

    private Long id;
    @NotNull(message = "Product name cannot null...!")
    private String productName;
    @NotNull(message = "Product description cannot null...!")
    private String productDesc;
    private String productCategory;
    @NotNull(message = "Product price cannot null...!")
    private Double productPrice;
    @NotNull(message = "Product media cannot null...!")
    private MediaDTO mediaDTO;
    private List<CategoryDTO> categories = new ArrayList();
    @NotNull(message = "Product categories  cannot null...!")
    private List<Long> categoriesIds = new ArrayList<>();
    private boolean deleted;
}
