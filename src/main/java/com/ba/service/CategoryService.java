package com.ba.service;

import com.ba.converter.CategoryConvertor;
import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
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

    public List<CategoryDTO> listCategory() {
        return CategoryConvertor.convertListToCategoryListDTO(categoryRepository.findAll());
    }

    public void addCategory(CategoryDTO categoryDTO) {
        categoryRepository.save(CategoryConvertor.convertDTOtoCategory(categoryDTO));
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        categoryRepository.saveAndFlush(CategoryConvertor.convertDTOtoCategory(categoryDTO));
        return categoryDTO;
    }

//    public List<Product> listProducts() {
//        return productRepository.findAll();
//    }

    public Set<ProductDTO> listProductsById(Long id) {
        Optional<Category> category=categoryRepository.findById(id);
        return CategoryConvertor.convertOptionalCategoryToSetDTO(category);
    }

    public void addProduct(ProductDTO productDTO, Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        Product product =  CategoryConvertor.convertDTOToProduct(productDTO);

        product.setCategory(category.get());
        category.get().getProducts().add(product);
        categoryRepository.save(category.get());
    }

//    public void deleteProduct(Long id) {
//        productRepository.deleteById(id);
//    }
//
//    //
//    public void updateProduct(Product product, Long id) {
//        Optional<Category> category = categoryRepository.findById(id);
//        //Optional<Product> product2 =category.get().getProducts().stream().filter(p -> p.getProductID() == product.getProductID()).findAny();
//
//        categoryRepository.saveAndFlush(category.get());
//    }
}
