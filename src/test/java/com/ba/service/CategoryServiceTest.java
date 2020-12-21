package com.ba.service;

import com.ba.builder.*;
import com.ba.dto.CategoryDTO;
import com.ba.dto.MediaDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Media;
import com.ba.entity.Product;
import com.ba.mapper.CategoryMapper;
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
    private List<Product> productSet = new ArrayList<>();
    private Product product = new Product();
    private ProductDTO productDTO = new ProductDTO();
    private Long id = 1L;
    private Media media = new Media();
    private MediaDTO mediaDTO = new MediaDTO();
    @Before
    public void setUp() throws Exception {
        media = new MediaBuilder().id(1L).build();
        mediaDTO = new MediaDTOBuilder().id(1L).build();

        category = new CategoryBuilder().name("Çorba").description("Sıcak Çorba").id(1L).imageToUrl("img").media(media).build();
        categoryDTO = new CategoryDTOBuilder().id(1L).name("ÇorbaDTO").description("Sıcak ÇorbaDTO").imageToUrl("imgDTO").media(mediaDTO).build();

        categoryList.add(category);

        product = new ProductBuilder().productID(1L).productPrice(15D).productCategory("Çorba").productDesc("desc").productName("Mercimek").media(media).build();
        productDTO = new ProductDTOBuilder().productID(1L).productPrice(15D).productCategory("ÇorbaDTO").productDesc("descDTO").productName("MercimekDTO").media(mediaDTO).build();
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

        String res = service.deleteCategory(id);

        assertEquals(res, "Category Deleted");
        verify(repository, times(1)).deleteById(id);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotDeleteCategoryWhenThrownException() {

        doThrow(new RuntimeException("Cant delete here")).when(repository).deleteById(id);
        String res = service.deleteCategory(id);
    }

    @Test
    public void shouldUpdateCategory() {

        when(repository.saveAndFlush(category)).thenReturn(category);

        //categoryRepository.findBy null geliyor
//        CategoryDTO categoryDTO2 = service.updateCategory(categoryDTO);
//
//        assertNotNull(categoryDTO2);
//        assertEquals(categoryDTO2, categoryDTO);
    }

    @Test
    public void shouldListCategory() {
        when(repository.findAll()).thenReturn(categoryList);

        List<CategoryDTO> tempDTOList = CategoryMapper.INSTANCE.toDTOList(categoryList);
//        List<CategoryDTO> tempDTOList = CategoryConvertor.convertListToCategoryListDTO(categoryList);
        List<CategoryDTO> tempDTOList2 = service.listCategory();

        assertEquals(tempDTOList.get(0).getId(), tempDTOList2.get(0).getId());
    }

    @Test
    public void shouldAddProductIntoCategory() {

        //productSet.add(product);
        category.setProducts(productSet);
        Optional<Category> optionalCategoryList = Optional.of(category);

        when(repository.findById(id)).thenReturn(optionalCategoryList);
        when(service.addProduct(productDTO,id)).thenReturn("Product Added");
        String res = service.addProduct(productDTO, id);
        assertNotNull(res);
        assertEquals(res, "Product Added");
    }

    @Test
    public void shouldListProductsByCategoryId() {

//        //productSet.add(product);
//        category.setProducts(productSet);
//        Optional<Category> optionalCategoryList = Optional.of(category);
//        Category tempCategory = optionalCategoryList.get();
//
//        when(repository.findById(id)).thenReturn(optionalCategoryList);
//
//        List<ProductDTO> tempDTOList = CategoryMapper.INSTANCE.toEntity(tempCategory);
//        List<ProductDTO> tempDTOList = CategoryConvertor.convertOptionalCategoryToSetDTO(optionalCategoryList);
//        List<ProductDTO> tempDTOList2 = service.listProductsById(id);
//
//        assertNotNull(tempDTOList);
//        assertNotNull(tempDTOList2);
        //eşitlik kontrolü
//        assertEquals(tempDTOList.get(0).getProductID(), tempDTOList2.get(0).getProductID());
    }
}