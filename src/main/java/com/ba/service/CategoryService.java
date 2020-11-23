package com.ba.service;

import com.ba.entity.Category;
import com.ba.entity.Product;
import com.ba.repository.CategoryRepository;
import com.ba.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    public List<Category> listCategory(){
        return categoryRepository.findAll();
    }

    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

    public Category updateCategory(Category category){
        return categoryRepository.saveAndFlush(category);
    }

    public List<Product> listProducts(){
        return productRepository.findAll();
    }

    public Set<Product> listProductsById(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        return category.get().getProducts();
    }

    public void addProduct(Product product,Long id){
        Optional<Category> category = categoryRepository.findById(id);
        category.get().getProducts().add(product);
        categoryRepository.save(category.get());
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public void updateProduct(Product product,Long id){
        //MUALLAKTA
        Optional<Category> category = categoryRepository.findById(id);
        //Optional<Product> product2 =category.get().getProducts().stream().filter(p -> p.getProductID() == product.getProductID()).findAny();

        categoryRepository.saveAndFlush(category.get());
    }
}
