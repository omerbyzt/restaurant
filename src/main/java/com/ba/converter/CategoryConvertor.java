package com.ba.converter;

import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;

import java.util.*;

public class CategoryConvertor {

    public static List<CategoryDTO> convertListToCategoryListDTO(List<Category> categoryList){

        List<CategoryDTO> categoryListDTO = new ArrayList<>();

        for(Category categoryListItem : categoryList){
            CategoryDTO categoryDTO = new CategoryDTO();

            categoryDTO.setDescription(categoryListItem.getDescription());
            categoryDTO.setId(categoryListItem.getId());
            categoryDTO.setImageToUrl(categoryListItem.getImageToUrl());
            categoryDTO.setName(categoryListItem.getName());
            categoryDTO.setProducts(categoryListItem.getProducts());

            categoryListDTO.add(categoryDTO);
        }

        return categoryListDTO;
    }

    public static Category convertDTOtoCategory(CategoryDTO categoryDTO){
        Category category = new Category();

        category.setProducts(categoryDTO.getProducts());
        category.setImageToUrl(categoryDTO.getImageToUrl());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setId(categoryDTO.getId());

        return category;
    }

    public static Set<ProductDTO> convertOptionalCategoryToSetDTO(Optional<Category> category){
        Set<ProductDTO> dto = new HashSet<>();

        for(Product prod: category.get().getProducts() ){
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductID(prod.getProductID());
            productDTO.setCategory(prod.getCategory());
            productDTO.setProductCategory(prod.getProductCategory());
            productDTO.setProductDesc(prod.getProductDesc());
            productDTO.setProductName(prod.getProductName());
            productDTO.setProductPrice(prod.getProductPrice());

            dto.add(productDTO);
        }
        return dto;
    }

    public static Product convertDTOToProduct(ProductDTO productDTO){
        Product product = new Product();

        product.setProductID(productDTO.getProductID());
        product.setProductPrice(productDTO.getProductPrice());
        product.setProductCategory(productDTO.getProductCategory());
        product.setProductDesc(productDTO.getProductDesc());
        product.setProductName(productDTO.getProductName());

        return product;
    }


}
