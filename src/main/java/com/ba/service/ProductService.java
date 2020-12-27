package com.ba.service;

import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.dto.ProductWrapperList;
import com.ba.dto.ProductWrapperSlicerList;
import com.ba.entity.Category;
import com.ba.entity.Product;
import com.ba.exception.SystemException;
import com.ba.mapper.CategoryMapper;
import com.ba.mapper.ProductMapper;
import com.ba.repository.CategoryRepository;
import com.ba.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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

    @Autowired
    private ProductMapper productMapper;

    public Page<ProductDTO> listProductPage(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        Page<ProductDTO> productDTOPage = productPage.map(productMapper::toDTO);
        if (productDTOPage.isEmpty()) {
            throw new SystemException("Products not found...!");
        }
        for (int i = 0; i < productDTOPage.getContent().size(); i++) {
            productDTOPage.getContent().get(i).setCategories(CategoryMapper.INSTANCE.toDTOList(productPage.getContent().get(i).getCategories()));
        }
        return productDTOPage;
    }

    public Slice<ProductDTO> listProductsByCategoryID(Pageable pageable, int id) {
        Slice<ProductDTO> productDTOSlice = productRepository.findProductByCategoriesId(pageable, (long) id).map(productMapper::toDTO);
        if (productDTOSlice.isEmpty()) {
            throw new SystemException("Products not found...!");
        }
        return productDTOSlice;
    }

    public String deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new SystemException("An error occured...!");
        }

        product.get().getCategories().forEach(category -> {
            category.getProducts().remove(product.get());
        });

        productRepository.delete(product.get());
//        productRepository.deleteById(id);
        return "Product Deleted";
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product product = productRepository.findById(productDTO.getId()).get();
        List<Category> categoryList = product.getCategories();

        for (int i = 0; i < categoryList.size(); i++) {
            categoryList.get(i).getProducts().remove(product);
            categoryRepository.save(categoryList.get(i));
        }

        List<Long> categoriesIdsList = productDTO.getCategoriesIds();
        List<Category> categoryList2 = new ArrayList<>();

        Product product2 = productMapper.toEntity(productDTO);
        List<CategoryDTO> tempCategoryDTOList = productDTO.getCategories();
        product2.setCategories(CategoryMapper.INSTANCE.toList(tempCategoryDTOList));

        for (int i = 0; i < categoriesIdsList.size(); i++) {
            Category category = categoryRepository.findById(categoriesIdsList.get(i)).get();
            category.getProducts().add(product2);
            categoryList2.add(category);
        }
        product2.setCategories(categoryList2);

        productRepository.save(product2);

        return productDTO;
    }

    public String addProduct(ProductDTO productDTO) {
        if (productDTO.getCategoriesIds().isEmpty()) {
            throw new SystemException("An error occured...!");
        }
        List<Long> categoriesIdsList = productDTO.getCategoriesIds();
        List<Category> categoryList = new ArrayList<>();
        Product product = productMapper.toEntity(productDTO);

        categoriesIdsList.forEach(categoryId -> {
            Optional<Category> category = categoryRepository.findById(categoryId);
            if (category.isEmpty()) {
                throw new SystemException("An error occured...!");
            }
            category.get().getProducts().add(product);
            categoryList.add(category.get());
        });

        product.setCategories(categoryList);
        productRepository.save(product);

        return "Product Added";
    }
}
