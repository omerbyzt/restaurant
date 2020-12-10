package com.ba.builder;

import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Product;

import java.util.List;
import java.util.Set;

public class CategoryDTOBuilder extends Builder{

    private String name;
    private String description;
    private String imageToUrl;
    private List<ProductDTO> products;

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

    public CategoryDTOBuilder imageToUrl(String imageToUrl){
        this.imageToUrl = imageToUrl;
        return this;
    }

    public CategoryDTOBuilder products(List<ProductDTO> products){
        this.products = products;
        return this;
    }

    @Override
    public CategoryDTO build() {
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setProducts(this.products);
        categoryDTO.setImageToUrl(this.imageToUrl);
        categoryDTO.setDescription(this.description);
        categoryDTO.setName(this.name);
        categoryDTO.setId(this.getId());

        return categoryDTO;
    }
}
