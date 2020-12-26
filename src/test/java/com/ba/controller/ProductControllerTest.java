package com.ba.controller;

import com.ba.builder.CategoryBuilder;
import com.ba.builder.ProductBuilder;
import com.ba.builder.ProductDTOBuilder;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;
import com.ba.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

    @Before
    public void setUp() throws Exception {

        category = new CategoryBuilder().id(1L).description("denemeDesc").name("denemeName").imageToUrl("denemeImg").build();

//        product = new ProductBuilder().category(category).productName("mercimek").productDesc("mercimek çorbası").productCategory("çorba").productPrice(15D).productID(1L).build();
//        productDTO = new ProductDTOBuilder().category(category).productName("mercimekDTO").productDesc("mercimek çorbasıDTO").productCategory("çorbaDTO").productPrice(15D).productID(1L).build();

        product = new ProductBuilder().productName("mercimek").productDesc("mercimek çorbası").productCategory("çorba").productPrice(15D).productID(1L).build();
        productDTO = new ProductDTOBuilder().productName("mercimekDTO").productDesc("mercimek çorbasıDTO").productCategory("çorbaDTO").productPrice(15D).productID(1L).build();

        productList.add(product);
        productListDTO.add(productDTO);
    }

//    @Test
//    public void shouldVerifyListAllProducts() {
//
//        when(service.listAllProducts()).thenReturn(productListDTO);
//        List<ProductDTO> tempDTOList = controller.listAllProducts();
//
//        assertEquals(tempDTOList,productListDTO);
//    }

    @Test
    public void shouldDeleteProduct() {
        when(service.deleteProduct(id)).thenReturn("Product Deleted");
        String res = controller.deleteProduct(id);

        assertEquals(res,"Product Deleted");
    }

    @Test
    public void shouldVerifyUpdateProduct() {
        when(service.updateProduct(productDTO)).thenReturn(productDTO);
        ProductDTO tempDTO = controller.updateProduct(productDTO);
        assertEquals(tempDTO,productDTO);
    }
}