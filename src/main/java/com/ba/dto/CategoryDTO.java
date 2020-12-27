package com.ba.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;
    private String description;
//    private String imageToUrl;
    private List<ProductDTO> products;
    private MediaDTO mediaDTO;
    private boolean deleted;

//    public MediaDTO getMedia() {
//        return mediaDTO;
//    }
//
//    public void setMedia(MediaDTO mediaDTO) {
//        this.mediaDTO = mediaDTO;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getImageToUrl() {
//        return imageToUrl;
//    }
//
//    public void setImageToUrl(String imageToUrl) {
//        this.imageToUrl = imageToUrl;
//    }
//
//    public List<ProductDTO> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<ProductDTO> products) {
//        this.products = products;
//    }
}
