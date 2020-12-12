package com.ba.service;

import com.ba.converter.CategoryConvertor;
import com.ba.converter.ProductConverter;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;
import com.ba.repository.CategoryRepository;
import com.ba.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> listAllProducts(){
        return ProductConverter.convertProductToProductDTO(productRepository.findAll());
    }

    public String deleteProduct(Long id){
        Product product = productRepository.findById(id).get();
        List<Category> categoryList = product.getCategories();

        for (int i = 0 ;i<categoryList.size();i++){
            categoryList.get(i).getProducts().remove(product);
            categoryRepository.save(categoryList.get(i));
        }

        productRepository.deleteById(id);
        return "Product Deleted";
    }

    public ProductDTO updateProduct(ProductDTO productDTO){

        Product product = productRepository.findById(productDTO.getProductID()).get();
        List<Category> categoryList = product.getCategories();

        for (int i = 0 ;i<categoryList.size();i++){
            categoryList.get(i).getProducts().remove(product);
            categoryRepository.save(categoryList.get(i));
        }

        //productRepository.saveAndFlush(ProductConverter.updateDTOToEntity(productDTO));

        List<Long> categoriesIdsList = productDTO.getCategoriesIds();
        List<Category> categoryList2 = new ArrayList<>();
        Product product2 = CategoryConvertor.convertDTOToProduct(productDTO);
        for(int i = 0 ; i<categoriesIdsList.size() ; i++){
            Category category = categoryRepository.findById(categoriesIdsList.get(i)).get();
            category.getProducts().add(product2);
            categoryList2.add(category);
        }
        product2.setCategories(categoryList2);

        productRepository.save(product2);

        return productDTO;
    }

}
