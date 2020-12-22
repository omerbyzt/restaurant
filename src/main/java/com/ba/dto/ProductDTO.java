package com.ba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long productID;
    private String productName;
    private String productDesc;
    private String productCategory;
    private Double productPrice;
    private MediaDTO mediaDTO;
    private List<CategoryDTO> categories = new ArrayList();
    private List<Long> categoriesIds = new ArrayList<>();
    private boolean deleted;

    //
    //private Category category;
    //private Set<Category>categories  = new HashSet<>();


//    public MediaDTO getMediaDTO() {
//        return mediaDTO;
//    }
//
//    public void setMediaDTO(MediaDTO mediaDTO) {
//        this.mediaDTO = mediaDTO;
//    }
//
//
//    public List<Long> getCategoriesIds() {
//        return categoriesIds;
//    }
//
//    public void setCategoriesIds(List<Long> categoriesIds) {
//        this.categoriesIds = categoriesIds;
//    }
//
//    public Long getProductID() {
//        return productID;
//    }
//
//    public void setProductID(Long productID) {
//        this.productID = productID;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public String getProductDesc() {
//        return productDesc;
//    }
//
//    public void setProductDesc(String productDesc) {
//        this.productDesc = productDesc;
//    }
//
//    public String getProductCategory() {
//        return productCategory;
//    }
//
//    public void setProductCategory(String productCategory) {
//        this.productCategory = productCategory;
//    }
//
//    public Double getProductPrice() {
//        return productPrice;
//    }
//
//    public void setProductPrice(Double productPrice) {
//        this.productPrice = productPrice;
//    }
//
//    public List<CategoryDTO> getCategories() {
//        return categories;
//    }
//
//    public void setCategories(List<CategoryDTO> categories) {
//        this.categories = categories;
//    }
//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
}
