package com.ba.controller;

import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;
import com.ba.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    @InjectMocks
    private CategoryController controller;

    @Mock
    private CategoryService service;

    private Category category = new Category();
    private CategoryDTO categoryDTO = new CategoryDTO();
    private List<Category> categoryList = new ArrayList<>();
    private List<CategoryDTO> categoryListDTO = new ArrayList<>();
    private Set<Product> productSetList = new HashSet<>();
    private Set<ProductDTO> productSetListDTO = new HashSet<>();
    private Product product = new Product();
    private ProductDTO productDTO = new ProductDTO();
    private Long id = 111L;

    @Before
    public void setUp() throws Exception {
        category.setImageToUrl("img");
        category.setName("Çorba");
        category.setDescription("Sıcak Çorba");
        category.setId(1L);

        categoryDTO.setImageToUrl("imgDTO");
        categoryDTO.setName("ÇorbaDTO");
        categoryDTO.setDescription("Sıcak ÇorbaDTO");
        categoryDTO.setId(2L);

        categoryList.add(category);

        product.setProductID(1L);
        product.setProductPrice(15D);
        product.setProductCategory("Çorba");
        product.setProductDesc("desc");
        product.setProductName("Mercimek");
        product.setCategory(category);

        productDTO.setProductID(1L);
        productDTO.setProductPrice(15D);
        productDTO.setProductCategory("ÇorbaDTO");
        productDTO.setProductDesc("descDTO");
        productDTO.setProductName("MercimekDTO");
        productDTO.setCategory(category);

        categoryListDTO.add(categoryDTO);
        productSetListDTO.add(productDTO);
    }

    @Test
    public void shouldVerifyListCategory() {
        when(service.listCategory()).thenReturn(categoryListDTO);
        List<CategoryDTO> tempDTOList = controller.listCategory();
        assertEquals(tempDTOList, categoryListDTO);
    }

    @Test
    public void shouldVerifyAddCategory() {
        when(service.addCategory(categoryDTO)).thenReturn("Category Added");
        String res = controller.addCategory(categoryDTO);
        assertEquals(res, "Category Added");
    }

    @Test
    public void shouldVerifyDeleteCategory() {
        when(service.deleteCategory(id)).thenReturn("Category Deleted");
        String res = controller.deleteCategory(id);
        assertEquals(res, "Category Deleted");
    }

    @Test
    public void shouldVerifyUpdateCategory() {
        when(service.updateCategory(categoryDTO)).thenReturn(categoryDTO);
        CategoryDTO tempDTO = controller.updateCategory(categoryDTO);
        assertEquals(tempDTO, categoryDTO);
    }

    @Test
    public void shouldVerifyListProductById() {
        when(service.listProductsById(id)).thenReturn(productSetListDTO);
        Set<ProductDTO> tempDTOList = controller.listProductsById(id);
        assertEquals(tempDTOList, productSetListDTO);
    }

    @Test
    public void shouldVerifyAddProduct() {
        when(service.addProduct(productDTO, id)).thenReturn("Product Added");
        String res = controller.addProduct(productDTO, id);
        assertEquals(res, "Product Added");
    }
}