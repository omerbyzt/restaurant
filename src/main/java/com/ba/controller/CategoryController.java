package com.ba.controller;

import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;
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
    public void addCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.addCategory(categoryDTO);
    }

    @DeleteMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return "Id = "+id+" deleted";
    }

    @PutMapping("/update-category")
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.updateCategory(categoryDTO);
    }

//    @GetMapping("/list-products")
//    public List<Product> listProducts(){
//        return categoryService.listProducts();
//    }

    @GetMapping("/list-products/{id}")
    public Set<ProductDTO> listProductsById(@PathVariable Long id){
        return categoryService.listProductsById(id);
    }

    @PostMapping("/add-product/{id}")
    public void addProduct(@RequestBody ProductDTO productDTO, @PathVariable Long id){
        categoryService.addProduct(productDTO,id);
    }

//    @DeleteMapping("/delete-product/{id}")
//    public void deleteProduct(@PathVariable Long id){
//        categoryService.deleteProduct(id);
//    }
//
//    @PutMapping("/update-product/{id}")
//    public void updateProduct(@RequestBody Product product,@PathVariable Long id){
//        categoryService.updateProduct(product,id);
//    }

}
