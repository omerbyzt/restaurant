package com.ba.service;

import com.ba.converter.ProductConverter;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;
import com.ba.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.parameters.P;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService service;

    @Mock
    private ProductRepository repository;

    private Product product = new Product();
    private ProductDTO productDTO = new ProductDTO();
    private List<Product> productList = new ArrayList<>();
    private List<ProductDTO> productListDTO = new ArrayList<>();
    private Category category = new Category();


    @Before
    public void setUp() throws Exception {
        category.setId(1L);
        category.setDescription("denemeDesc");
        category.setName("denemeName");
        category.setImageToUrl("denemeImg");

        product.setCategory(category);
        product.setProductName("mercimek");
        product.setProductDesc("mercimek çorbası");
        product.setProductCategory("çorba");
        product.setProductPrice(15D);
        product.setProductID(1L);

        productDTO.setCategory(category);
        productDTO.setProductName("mercimekDTO");
        productDTO.setProductDesc("mercimek çorbasıDTO");
        productDTO.setProductCategory("çorbaDTO");
        productDTO.setProductPrice(15D);
        productDTO.setProductID(1L);

        productList.add(product);
        productListDTO.add(productDTO);
    }

    @Test
    public void shouldDeleteProduct() {
        long id = 111;

        String res = service.deleteProduct(id);

        assertEquals(res,"Product Deleted");
        verify(repository,times(1)).deleteById(id);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotDeleteProductWhenThrownException() {
        long id = 111;

        doThrow(new RuntimeException("Cant delete here")).when(repository).deleteById(id);
        String res = service.deleteProduct(id);
    }

    @Test
    public void shouldUpdateProduct() {
        when(repository.saveAndFlush(product)).thenReturn(product);

        ProductDTO productDTO2 = service.updateProduct(productDTO);

        assertNotNull(productDTO2);
        assertEquals(productDTO2,productDTO);
    }

    @Test
    public void shouldListProduct() {
        when(repository.findAll()).thenReturn(productList);

        List<ProductDTO> tempDTOList = ProductConverter.convertProductToProductDTO(productList);
        List<ProductDTO> tempDTOList2 = service.listAllProducts();

        assertEquals(tempDTOList.get(0).getProductID(),tempDTOList2.get(0).getProductID());
    }
}