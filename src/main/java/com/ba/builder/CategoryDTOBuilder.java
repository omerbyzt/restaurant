package com.ba.builder;

import com.ba.dto.CategoryDTO;
import com.ba.dto.MediaDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Product;

import java.util.List;
import java.util.Set;

public class CategoryDTOBuilder extends Builder{

    private String name;
    private String description;
    private List<ProductDTO> products;
    private MediaDTO mediaDTO;

    public CategoryDTOBuilder id(Long id){
        this.setId(id);
        return this;
    }

    public CategoryDTOBuilder name(String name){
        this.name = name;
        return this;
    }

    public CategoryDTOBuilder description(String description){
        this.description = description;
        return this;
    }

    public CategoryDTOBuilder products(List<ProductDTO> products){
        this.products = products;
        return this;
    }

    public CategoryDTOBuilder media(MediaDTO mediaDTO){
        this.mediaDTO = mediaDTO;
        return this;
    }

    @Override
    public CategoryDTO build() {
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setProducts(this.products);
        categoryDTO.setDescription(this.description);
        categoryDTO.setName(this.name);
        categoryDTO.setId(this.getId());
        categoryDTO.setMediaDTO(this.mediaDTO);//

        return categoryDTO;
    }
}
