package com.ba.controller;

import com.ba.entity.Product;
import com.ba.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/listall")
    public List<Product> listAllProducts(){
        return productService.listAllProducts();
    }

    @PostMapping("/add")
    public List<Product> addNews(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public List<Product> deleteNews(@PathVariable Long id){
        return productService.deleteProduct(id);
    }

    @PutMapping("/update/{id}")
    public List<Product> updateNews(@PathVariable long id,@RequestBody Product product){
        return productService.updateProduct(id,product);
    }

    @GetMapping("/listcategory")
    public List<String> listAllCategories(){
        List<String> tmpList =  productService.listAllCategories();
        return productService.listAllCategories();
    }

    @GetMapping("listcategory/{categoryName}")
    public List<Product> listSelectedCategory(@PathVariable String categoryName){
        return productService.listSelectedCategory(categoryName);
    }
}
