package com.ba.service;

import com.ba.entity.Product;
import com.ba.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> listAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> addProduct(Product product){
        productRepository.save(product);
        return listAllProducts();
    }

    public List<Product> deleteProduct(Long id){
        productRepository.deleteById(id);
        return listAllProducts();
    }

    public List<Product> updateProduct(long id,Product news){

        Optional<Product> optionalProductList = listAllProducts().stream().filter(product1 -> product1.getProductID() == id).findAny();

        if(optionalProductList == null){
            System.out.println("Ürün Bulunamadı");
            return null;
        }
        optionalProductList.get().setProductName(news.getProductName());
        optionalProductList.get().setProductDesc(news.getProductDesc());
        optionalProductList.get().setProductCategory(news.getProductCategory());
        optionalProductList.get().setProductPrice(news.getProductPrice());

        productRepository.saveAndFlush(optionalProductList.get());
        return listAllProducts();
    }

    public List<String> listAllCategories(){
        return productRepository.listAllCategories();
    }

    public List<Product> listSelectedCategory(String categoryName){
        return productRepository.listSelectedCategory(categoryName);
    }
}
