package com.ba.controller;

import com.ba.dto.ProductDTO;
import com.ba.entity.Product;
import com.ba.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/listall")
    public List<ProductDTO> listAllProducts(){
        return productService.listAllProducts();
    }

//    @PostMapping("/add")
//    public void addNews(@RequestBody Product product){
//        productService.addProduct(product);
//    }

    @DeleteMapping("/delete/{id}")
    public void deleteNews(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @PutMapping("/update/{id}")
    public List<ProductDTO> updateNews(@RequestBody ProductDTO productDTO){
        return productService.updateProduct(productDTO);
    }

//    @GetMapping("/listcategory")
//    public List<String> listAllCategories(){
//        List<String> tmpList =  productService.listAllCategories();
//        return productService.listAllCategories();
//    }

//    @GetMapping("listcategory/{categoryName}")
//    public List<Product> listSelectedCategory(@PathVariable String categoryName){
//        return productService.listSelectedCategory(categoryName);
//    }
}
