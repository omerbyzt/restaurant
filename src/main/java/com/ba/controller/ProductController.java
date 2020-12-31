package com.ba.controller;

import com.ba.dto.ProductDTO;
import com.ba.exception.BusinessRuleException;
import com.ba.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/listmore")
    public Page<ProductDTO> listProductPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.listProductPage(pageable);
    }

    @GetMapping("/listslice")
    public Slice<ProductDTO> listProductsByCategoryID(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "1") int categoryId) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.listProductsByCategoryID(pageable, categoryId);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        if(id == null){
            throw new BusinessRuleException("Id cannot be empty...!");
        }
        productService.deleteProduct(id);
        return "Product Deleted";
    }

    @PutMapping("/update")
    public ProductDTO updateProduct(@Valid @RequestBody ProductDTO productDTO) {
        productService.updateProduct(productDTO);
        return productDTO;
    }

    @PostMapping("/add-product")
    public String  addProduct(@Valid @RequestBody ProductDTO productDTO){
        productService.addProduct(productDTO);
        return "Product Added";
    }
}
