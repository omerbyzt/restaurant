package com.ba.dto;

import com.ba.entity.Media;
import com.ba.entity.Product;

import java.util.List;
import java.util.Set;

public class CategoryDTO {

    private Long id;
    private String name;
    private String description;
    private String imageToUrl;
    private List<ProductDTO> products;
    private MediaDTO mediaDTO;

    public MediaDTO getMedia() {
        return mediaDTO;
    }

    public void setMedia(MediaDTO mediaDTO) {
        this.mediaDTO = mediaDTO;
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

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
