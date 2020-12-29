package com.ba.service;

import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;
import com.ba.exception.SystemException;
import com.ba.helper.UpdateHelper;
import com.ba.mapper.CategoryMapper;
import com.ba.mapper.MediaMapper;
import com.ba.mapper.ProductMapper;
import com.ba.repository.CategoryRepository;
import com.ba.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private MediaMapper mediaMapper;

    public Page<ProductDTO> listProductPage(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        Page<ProductDTO> productDTOPage = productPage.map(productMapper::toDTO);
        if (productDTOPage.isEmpty()) {
            throw new SystemException("Products not found...!");
        }
        for (int i = 0; i < productDTOPage.getContent().size(); i++) {
            productDTOPage.getContent().get(i).setCategories(categoryMapper.toDTOList(productPage.getContent().get(i).getCategories()));
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

    @Transactional(propagation = Propagation.REQUIRED)
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

    @Transactional(propagation = Propagation.REQUIRED)
    public ProductDTO updateProduct(ProductDTO productDTO) {
        Optional<Product> product = productRepository.findById(productDTO.getId());
        if (product.isEmpty()) {
            throw new SystemException("Product not found...!");
        }

        UpdateHelper.productSetCheck(productDTO, product, categoryRepository, mediaMapper, categoryMapper);
        productRepository.save(product.get());
        return productDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
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
