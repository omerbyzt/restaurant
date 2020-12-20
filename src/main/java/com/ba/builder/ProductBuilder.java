package com.ba.builder;

import com.ba.entity.Category;
import com.ba.entity.Media;
import com.ba.entity.Product;

import java.util.List;
import java.util.Set;

public class ProductBuilder {

    private Long productID;
    private String productName;
    private String productDesc;
    private String productCategory;
    private Double productPrice;
    //private Category category;
    private List<Category> categories;
    private Media media;

    public ProductBuilder productID(Long productID){
        this.productID = productID;
        return this;
    }

    public ProductBuilder productName(String productName){
        this.productName = productName;
        return this;
    }

    public ProductBuilder productDesc(String productDesc){
        this.productDesc = productDesc;
        return this;
    }

    public ProductBuilder productCategory(String productCategory){
        this.productCategory = productCategory;
        return this;
    }

    public ProductBuilder productPrice(Double productPrice){
        this.productPrice = productPrice;
        return this;
    }

    public ProductBuilder categories(List<Category> categories){
        this.categories = categories;
        return this;
    }

    public ProductBuilder media(Media media){
        this.media = media;
        return this;
    }

    public Product build(){
        Product product = new Product();

        product.setProductID(this.productID);
        product.setProductName(this.productName);
        product.setProductDesc(this.productDesc);
        product.setProductCategory(this.productCategory);
        product.setProductPrice(this.productPrice);
        product.setCategories(this.categories);
        product.setMedia(this.media);

        return product;
    }
}
