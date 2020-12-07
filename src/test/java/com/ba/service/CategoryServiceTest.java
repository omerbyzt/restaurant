package com.ba.service;

import com.ba.converter.CategoryConvertor;
import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;
import com.ba.repository.CategoryRepository;
import com.ba.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {
    @InjectMocks
    private CategoryService service;

    @Mock
    private CategoryRepository repository;

    @Mock
    private ProductRepository productRepository;

    private Category category = new Category();
    private CategoryDTO categoryDTO = new CategoryDTO();
    private List<Category> categoryList = new ArrayList<>();
    private Set<Product> productSet = new HashSet<>();
    private Product product = new Product();
    private ProductDTO productDTO = new ProductDTO();

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

    }

    @Test
    public void shouldAddNewCategory() {
        when(repository.save(category)).thenReturn(category);

        String res = service.addCategory(categoryDTO);

        assertNotNull(res);
        assertEquals(res, "Category Added");
    }

    @Test
    public void shouldDeleteCategory() {
        long id = 111;

        String res = service.deleteCategory(id);

        assertEquals(res, "Category Deleted");
        verify(repository, times(1)).deleteById(id);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotDeleteCategoryWhenThrownException() {
        long id = 111;

        doThrow(new RuntimeException("Cant delete here")).when(repository).deleteById(id);
        String res = service.deleteCategory(id);
    }

    @Test
    public void shouldUpdateCategory() {
        when(repository.saveAndFlush(category)).thenReturn(category);

        CategoryDTO categoryDTO2 = service.updateCategory(categoryDTO);

        assertNotNull(categoryDTO2);
        assertEquals(categoryDTO2, categoryDTO);
    }

    @Test
    public void shouldListCategory() {
        when(repository.findAll()).thenReturn(categoryList);

        List<CategoryDTO> tempDTOList = CategoryConvertor.convertListToCategoryListDTO(categoryList);
        List<CategoryDTO> tempDTOList2 = service.listCategory();

        assertEquals(tempDTOList.get(0).getId(), tempDTOList2.get(0).getId());
    }

    @Test
    public void shouldAddProductIntoCategory() {
        Long id = 1L;
        productSet.add(product);
        category.setProducts(productSet);
        Optional<Category> optionalCategoryList = Optional.of(category);

        when(repository.findById(id)).thenReturn(optionalCategoryList);

        String res = service.addProduct(productDTO, id);
        assertNotNull(res);
        assertEquals(res, "Product Added");
    }

    @Test
    public void shouldListProductsByCategoryId() {
        Long id = 1L;
        productSet.add(product);
        category.setProducts(productSet);
        Optional<Category> optionalCategoryList = Optional.of(category);

        when(repository.findById(id)).thenReturn(optionalCategoryList);

        Set<ProductDTO> tempDTOList = CategoryConvertor.convertOptionalCategoryToSetDTO(optionalCategoryList);
        Set<ProductDTO> tempDTOList2 = service.listProductsById(id);

        assertEquals(tempDTOList.iterator().next().getProductID(), tempDTOList2.iterator().next().getProductID());
    }
}