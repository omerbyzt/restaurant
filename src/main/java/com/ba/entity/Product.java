package com.ba.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;
    private String productName;
    private String productDesc;
    private String productCategory;
    private Double productPrice;

//    @ManyToOne
//    @JoinColumn(
//            name = "category_id"
//    )
//    private Category category;

    @JsonBackReference
    @ManyToMany(mappedBy = "products", cascade = CascadeType.DETACH)
    private List<Category> categories = new ArrayList<>();

    public Product(String productName, String productDesc, String productCategory, Double productPrice) {
        this.productName = productName;
        this.productDesc = productDesc;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Product() {

    }

//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }
}
