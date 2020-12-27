package com.ba.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String productName;
    private String productDesc;
    private String productCategory;
    private Double productPrice;
    private MediaDTO mediaDTO;
    private List<CategoryDTO> categories = new ArrayList();
    private List<Long> categoriesIds = new ArrayList<>();
    private boolean deleted;
}
