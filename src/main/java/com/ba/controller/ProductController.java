package com.ba.controller;

import com.ba.dto.ProductDTO;
import com.ba.dto.ProductWrapperList;
import com.ba.dto.ProductWrapperSlicerList;
import com.ba.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/listall")
    public List<ProductDTO> listAllProducts() {
        return productService.listAllProducts();
    }

    @GetMapping("/listmore")
    public ProductWrapperList listProductPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.listProductPage(pageable);
    }

    @GetMapping("/listslice")
    public ProductWrapperSlicerList listProductsByCategoryID(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "1") int categoryId) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.listProductsByCategoryID(pageable, categoryId);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product Deleted";
    }

    @PutMapping("/update")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        productService.updateProduct(productDTO);
        return productDTO;
    }
}
