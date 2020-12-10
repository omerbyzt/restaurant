package com.ba.builder;

import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductDTOBuilder {

    private Long productID;
    private String productName;
    private String productDesc;
    private String productCategory;
    private Double productPrice;
    //private Category category;
    private List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTOBuilder productID(Long productID){
        this.productID = productID;
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

//    public ProductDTOBuilder category(Category category){
//        this.category=category;
//        return this;
//    }

    public ProductDTOBuilder categories(List<CategoryDTO> categories){
        this.categories = categories;
        return this;
    }

    public ProductDTO build(){
        ProductDTO productDTO = new ProductDTO();

        productDTO.setProductID(this.productID);
        productDTO.setProductName(this.productName);
        productDTO.setProductDesc(this.productDesc);
        productDTO.setProductCategory(this.productCategory);
        productDTO.setProductPrice(this.productPrice);
        productDTO.setCategories(this.categories);
//        productDTO.setCategory(this.category);

        return productDTO;
    }
}
