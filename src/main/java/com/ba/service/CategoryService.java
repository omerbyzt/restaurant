package com.ba.service;

import com.ba.converter.CategoryConvertor;
import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;
import com.ba.repository.CategoryRepository;
import com.ba.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    public List<CategoryDTO> listCategory() {
        return CategoryConvertor.convertListToCategoryListDTO(categoryRepository.findAll());
    }

    public String addCategory(CategoryDTO categoryDTO) {
        categoryRepository.save(CategoryConvertor.convertDTOtoCategory(categoryDTO));
        return "Category Added";
    }

    public String deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return "Category Deleted";
    }

    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        categoryRepository.saveAndFlush(CategoryConvertor.convertDTOtoCategory(categoryDTO));
        return categoryDTO;
    }

    public List<ProductDTO> listProductsById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return CategoryConvertor.convertOptionalCategoryToSetDTO(category);
    }

    public String addProduct(ProductDTO productDTO, Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        Product product = CategoryConvertor.convertDTOToProduct(productDTO);

       // product.setCategory(category.get());
//        category.get().getProducts().add(product);
//        categoryRepository.save(category.get());

        return "Product Added";
    }
}
