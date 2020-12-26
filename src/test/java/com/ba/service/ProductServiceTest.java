package com.ba.service;

import com.ba.builder.*;
import com.ba.dto.MediaDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Media;
import com.ba.entity.Product;
import com.ba.mapper.ProductMapper;
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
    private Long id = 1L;
    private Media media = new Media();
    private MediaDTO mediaDTO = new MediaDTO();
    private List<Category> categories = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        media = new MediaBuilder().id(id).build();
        mediaDTO = new MediaDTOBuilder().id(id).build();

        category = new CategoryBuilder().id(1L).description("denemeDesc").name("denemeName").imageToUrl("denemeImg").media(media).build();
        categories.add(category);

        product = new ProductBuilder().productName("mercimek").productDesc("mercimek çorbası").productCategory("çorba").productPrice(15D).productID(1L).media(media).categories(categories).build();
        productDTO = new ProductDTOBuilder().productName("mercimekDTO").productDesc("mercimek çorbasıDTO").productCategory("çorbaDTO").productPrice(15D).productID(1L).media(mediaDTO).build();

        productList.add(product);
        productListDTO.add(productDTO);
    }

    @Test
    public void shouldDeleteProduct() {

//        String res = service.deleteProduct(id);
//
//        assertEquals(res,"Product Deleted");
//        verify(repository,times(1)).deleteById(id);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotDeleteProductWhenThrownException() {

        doThrow(new RuntimeException("Cant delete here")).when(repository).deleteById(id);
        String res = service.deleteProduct(id);
    }

    @Test
    public void shouldUpdateProduct() {

//        when(repository.saveAndFlush(product)).thenReturn(product);
//
//        ProductDTO productDTO2 = service.updateProduct(productDTO);
//
//        assertNotNull(productDTO2);
//        assertEquals(productDTO2,productDTO);
    }

//    @Test
//    public void shouldListProduct() {
//
//        when(repository.findAll()).thenReturn(productList);
//
//        List<ProductDTO> tempDTOList = ProductMapper.INSTANCE.toDTOList(productList);
////        List<ProductDTO> tempDTOList = ProductConverter.convertProductToProductDTO(productList);
//        List<ProductDTO> tempDTOList2 = service.listAllProducts();
//
//        assertEquals(tempDTOList.get(0).getId(),tempDTOList2.get(0).getId());
//    }
}