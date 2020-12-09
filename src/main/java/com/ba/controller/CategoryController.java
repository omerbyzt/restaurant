package com.ba.controller;

import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
    public String addCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.addCategory(categoryDTO);
        return "Category Added";
    }

    @DeleteMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return "Category Deleted";
    }

    @PutMapping("/update-category")
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.updateCategory(categoryDTO);
    }

    @GetMapping("/list-products/{id}")
    public List<ProductDTO> listProductsById(@PathVariable Long id){
        return categoryService.listProductsById(id);
    }

    @PostMapping("/add-product/{id}")
    public String  addProduct(@RequestBody ProductDTO productDTO, @PathVariable Long id){
        categoryService.addProduct(productDTO,id);
        return "Product Added";
    }
}
