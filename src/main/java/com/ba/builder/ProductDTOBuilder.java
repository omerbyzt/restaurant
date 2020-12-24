package com.ba.builder;

import com.ba.dto.CategoryDTO;
import com.ba.dto.MediaDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Media;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductDTOBuilder {

    private Long id;
    private String productName;
    private String productDesc;
    private String productCategory;
    private Double productPrice;
    private List<CategoryDTO> categories = new ArrayList<>();
    private MediaDTO mediaDTO;

    public ProductDTOBuilder productID(Long productID){
        this.id = productID;
        return this;
    }

    public ProductDTOBuilder productName(String productName){
        this.productName = productName;
        return this;
    }

    public ProductDTOBuilder productDesc(String productDesc){
        this.productDesc = productDesc;
        return this;
    }

    public ProductDTOBuilder productCategory(String productCategory){
        this.productCategory = productCategory;
        return this;
    }

    public ProductDTOBuilder productPrice(Double productPrice){
        this.productPrice = productPrice;
        return this;
    }

    public ProductDTOBuilder categories(List<CategoryDTO> categories){
        this.categories = categories;
        return this;
    }

    public ProductDTOBuilder media(MediaDTO mediaDTO){
        this.mediaDTO = mediaDTO;
        return this;
    }

    public ProductDTO build(){
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(this.id);
        productDTO.setProductName(this.productName);
        productDTO.setProductDesc(this.productDesc);
        productDTO.setProductCategory(this.productCategory);
        productDTO.setProductPrice(this.productPrice);
        productDTO.setCategories(this.categories);
        productDTO.setMediaDTO(this.mediaDTO);

        return productDTO;
    }
}
