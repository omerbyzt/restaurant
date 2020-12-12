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

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "Product Deleted";
    }

    @PutMapping("/update")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO){
        productService.updateProduct(productDTO);
        return productDTO;
    }
}
