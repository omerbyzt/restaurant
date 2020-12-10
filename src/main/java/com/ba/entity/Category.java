package com.ba.entity;

import com.ba.dto.ProductDTO;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
public class Category{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String imageToUrl;
//
//    @JsonIgnore
//    @OneToMany(
//            mappedBy = "category",
//            cascade = CascadeType.ALL
//    )
    @JsonManagedReference
    @ManyToMany
    @JoinTable(name="CATEGORY_PRODUCT" , joinColumns = @JoinColumn(name="category_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(
//            name = "media_id"
//    )
//    private Media mediaID;

    public Category(String name, String description, String imageToUrl) {
        this.name = name;
        this.description = description;
        this.imageToUrl = imageToUrl;
    }

    public Category() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageToUrl() {
        return imageToUrl;
    }

    public void setImageToUrl(String imageToUrl) {
        this.imageToUrl = imageToUrl;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
