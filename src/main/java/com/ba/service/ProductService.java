package com.ba.service;

import com.ba.converter.ProductConverter;
import com.ba.dto.ProductDTO;
import com.ba.entity.Product;
import com.ba.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<ProductDTO> listAllProducts(){
        return ProductConverter.convertProductToProductDTO(productRepository.findAll());
    }

    public String deleteProduct(Long id){
        productRepository.deleteById(id);
        return "Product Deleted";
    }

    public ProductDTO updateProduct(ProductDTO productDTO){
        productRepository.saveAndFlush(ProductConverter.convertDTOToProduct(productDTO));
        return productDTO;
    }

}
