package com.ba.builder;

import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Media;
import com.ba.entity.Product;

import java.util.List;
import java.util.Set;

public class CategoryBuilder extends Builder{

    private String name;
    private String description;
    private String imageToUrl;
    private List<Product> products;
    private Media media;

    public CategoryBuilder id(Long id){
        this.setId(id);
        return this;
    }

    public CategoryBuilder name(String name){
        this.name = name;
        return this;
    }

    public CategoryBuilder description(String description){
        this.description = description;
        return this;
    }

    public CategoryBuilder imageToUrl(String imageToUrl){
        this.imageToUrl = imageToUrl;
        return this;
    }

    public CategoryBuilder products(List<Product> products){
        this.products = products;
        return this;
    }

    public CategoryBuilder media(Media media){
        this.media = media;
        return this;
    }

    @Override
    public Category build() {
        Category category = new Category();

        category.setProducts(this.products);
        category.setImageToUrl(this.imageToUrl);
        category.setDescription(this.description);
        category.setName(this.name);
        category.setId(this.getId());
        category.setMedia(this.media);

        return category;
    }
}
