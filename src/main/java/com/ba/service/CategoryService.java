package com.ba.service;

import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;
import com.ba.mapper.CategoryMapper;
import com.ba.mapper.MediaMapper;
import com.ba.mapper.ProductMapper;
import com.ba.repository.CategoryRepository;
import com.ba.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<CategoryDTO> listCategory() {
        List<Category> tempCategoryList = categoryRepository.findAll();
        List<CategoryDTO> tempCategoryDTOList = CategoryMapper.INSTANCE.toDTOList(tempCategoryList);

        for(int i = 0; i<tempCategoryList.size(); i++){
            tempCategoryDTOList.get(i).setMediaDTO(MediaMapper.INSTANCE.toDTO(tempCategoryList.get(i).getMedia()));
        }

        return tempCategoryDTOList;
//        return CategoryMapper.INSTANCE.toDTOList(categoryRepository.findAll());
//        return CategoryConvertor.convertListToCategoryListDTO(categoryRepository.findAll());
    }

    public String addCategory(CategoryDTO categoryDTO) {
        categoryRepository.save(CategoryMapper.INSTANCE.toEntity(categoryDTO));
//        categoryRepository.save(CategoryConvertor.convertDTOtoCategory(categoryDTO));
        return "Category Added";
    }

    public String deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return "Category Deleted";
    }

    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        List<Product> tempProducts = categoryRepository.findById(categoryDTO.getId()).get().getProducts();
        Category tempCategory = CategoryMapper.INSTANCE.toEntity(categoryDTO);
        tempCategory.setProducts(tempProducts);
        categoryRepository.saveAndFlush(tempCategory);
//        Category tempCategory = categoryRepository.findById(categoryDTO.getId()).get();
//        categoryRepository.saveAndFlush(CategoryConvertor.convertDTOToCategorywithProducts(categoryDTO,tempCategory));
        return categoryDTO;
    }

    public List<ProductDTO> listProductsById(Long id) {
        Category category = categoryRepository.findById(id).get();
        List<ProductDTO> tempProductList = ProductMapper.INSTANCE.toDTOList(category.getProducts());

        return tempProductList;
    }

    public String addProduct(ProductDTO productDTO, Long id) {

        List<Long> categoriesIdsList = productDTO.getCategoriesIds();
        List<Category> categoryList = new ArrayList<>();
        Product product = ProductMapper.INSTANCE.toEntity(productDTO);
//        Product product = CategoryConvertor.convertDTOToProduct(productDTO);
        for(int i = 0 ; i<categoriesIdsList.size() ; i++){
            Category category = categoryRepository.findById(categoriesIdsList.get(i)).get();
            category.getProducts().add(product);
            categoryList.add(category);
        }

        product.setCategories(categoryList);
        productRepository.save(product);

        return "Product Added";
    }
}
