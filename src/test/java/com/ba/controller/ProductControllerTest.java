package com.ba.controller;

import com.ba.builder.CategoryBuilder;
import com.ba.builder.ProductBuilder;
import com.ba.builder.ProductDTOBuilder;
import com.ba.dto.CustomerDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;
import com.ba.exception.BusinessRuleException;
import com.ba.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController controller;

    @Mock
    private ProductService service;

    private Product product = new Product();
    private ProductDTO productDTO = new ProductDTO();
    private List<Product> productList = new ArrayList<>();
    private List<ProductDTO> productListDTO = new ArrayList<>();
    private Category category = new Category();
    private Long id = 111L;

    private Pageable pageable = PageRequest.of(0, 10);

    @Before
    public void setUp() throws Exception {

        category = new CategoryBuilder().id(1L).description("denemeDesc").name("denemeName").build();

        product = new ProductBuilder().productName("mercimek").productDesc("mercimek çorbası").productCategory("çorba").productPrice(15D).productID(1L).build();
        productDTO = new ProductDTOBuilder().productName("mercimekDTO").productDesc("mercimek çorbasıDTO").productCategory("çorbaDTO").productPrice(15D).productID(1L).build();

        productList.add(product);
        productListDTO.add(productDTO);
    }

    @Test
    public void shouldDeleteProduct() {
        when(service.deleteProduct(id)).thenReturn("Product Deleted");
        String res = controller.deleteProduct(id);

        assertEquals(res, "Product Deleted");
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotDeleteProduct() {
        when(service.deleteProduct(id)).thenReturn("Product Deleted");
        String res = controller.deleteProduct(null);
    }

    @Test
    public void shouldVerifyUpdateProduct() {
        when(service.updateProduct(productDTO)).thenReturn(productDTO);
        ProductDTO tempDTO = controller.updateProduct(productDTO);
        assertEquals(tempDTO, productDTO);
    }

    @Test
    public void shouldListProductPage() {
        Page<ProductDTO> productDTOPage = new PageImpl<ProductDTO>(productListDTO);
        when(service.listProductPage(pageable)).thenReturn(productDTOPage);
        Page<ProductDTO> tempCustomerDTOPage = controller.listProductPage(0, 10);

        assertNotNull(tempCustomerDTOPage);
        assertEquals(tempCustomerDTOPage, productDTOPage);
    }

    @Test
    public void shouldListProductsByCategoryID() {
        Slice<ProductDTO> productDTOSlice = new PageImpl<ProductDTO>(productListDTO);
        when(service.listProductsByCategoryID(pageable, 1)).thenReturn(productDTOSlice);
        Slice<ProductDTO> tempProductDTOSlice = controller.listProductsByCategoryID(0, 10, 1);

        assertNotNull(tempProductDTOSlice);
        assertEquals(tempProductDTOSlice, productDTOSlice);
    }

    @Test
    public void shouldAddProduct() {
        when(service.addProduct(productDTO)).thenReturn("Product Added");
        String res = controller.addProduct(productDTO);

        assertNotNull(res);
        assertEquals(res, "Product Added");
    }
}