package com.ba.service;

import com.ba.dto.CategoryDTO;
import com.ba.entity.Category;
import com.ba.exception.SystemException;
import com.ba.helper.UpdateHelper;
import com.ba.mapper.CategoryMapper;
import com.ba.repository.CategoryRepository;
import com.ba.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Cacheable(value = "CategoryCache")
    public List<CategoryDTO> listCategory() {
        List<Category> tempCategoryList = categoryRepository.findAll();
        if(tempCategoryList.isEmpty()){
            throw new SystemException("Categories not found...!");
        }
        return CategoryMapper.INSTANCE.toDTOList(tempCategoryList);
    }

    @CacheEvict(value="CategoryCache", allEntries = true)
    public String addCategory(CategoryDTO categoryDTO) {
        categoryRepository.save(CategoryMapper.INSTANCE.toEntity(categoryDTO));
        return "Category Added";
    }

    @CacheEvict(value="CategoryCache", allEntries = true)
    public String deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return "Category Deleted";
    }

    @CacheEvict(value="CategoryCache", allEntries = true)
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        Optional<Category> tempCategory =  categoryRepository.findById(categoryDTO.getId());
        if(tempCategory.isEmpty()){
            throw new SystemException("Category not found...!");
        }

        UpdateHelper.categorySetCheck(categoryDTO, tempCategory);

        categoryRepository.saveAndFlush(tempCategory.get());
        return CategoryMapper.INSTANCE.toDTO(tempCategory.get());
    }
}
