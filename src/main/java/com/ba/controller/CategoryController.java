package com.ba.controller;

import com.ba.dto.CategoryDTO;
import com.ba.exception.BusinessRuleException;
import com.ba.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/list-category")
    public List<CategoryDTO> listCategory(){
        return categoryService.listCategory();
    }

    @PostMapping("/add-category")
    public String addCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        categoryService.addCategory(categoryDTO);
        return "Category Added";
    }

    @DeleteMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable Long id){
        if(id == null){
            throw new BusinessRuleException("Id cannot be empty...!");
        }
        categoryService.deleteCategory(id);
        return "Category Deleted";
    }

    @PutMapping("/update-category")
    public CategoryDTO updateCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        return categoryService.updateCategory(categoryDTO);
    }

}
