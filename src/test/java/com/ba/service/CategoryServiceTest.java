package com.ba.service;

import com.ba.builder.*;
import com.ba.dto.CategoryDTO;
import com.ba.dto.CustomerDTO;
import com.ba.dto.MediaDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Media;
import com.ba.entity.Product;
import com.ba.exception.SystemException;
import com.ba.mapper.CategoryMapper;
import com.ba.mapper.MediaMapper;
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

    @Mock
    private CategoryMapper categoryMapper;

    @Mock
    private MediaMapper mediaMapper;

    private Category category;
    private CategoryDTO categoryDTO;
    private List<Category> categoryList = new ArrayList<>();
    private List<CategoryDTO> categoryDTOList = new ArrayList<>();
    private List<Product> productSet = new ArrayList<>();
    private Product product;
    private ProductDTO productDTO;
    private Long id = 1L;
    private Media media;
    private MediaDTO mediaDTO;

    @Before
    public void setUp() throws Exception {

        media = new MediaBuilder().id(1L).build();
        mediaDTO = new MediaDTOBuilder().id(1L).build();

        category = new CategoryBuilder().name("Çorba").description("Sıcak Çorba").id(1L).media(media).build();
        categoryDTO = new CategoryDTOBuilder().id(1L).name("ÇorbaDTO").description("Sıcak ÇorbaDTO").media(mediaDTO).build();

        categoryList.add(category);
        categoryDTOList.add(categoryDTO);

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
        when(repository.findById(id)).thenReturn(Optional.of(category));
        when(repository.saveAndFlush(category)).thenReturn(category);
        when(categoryMapper.toDTO(category)).thenReturn(categoryDTO);
        when(mediaMapper.toEntity(mediaDTO)).thenReturn(media);

        CategoryDTO result = service.updateCategory(categoryDTO);
        verify(repository).saveAndFlush(category);

        assertNotNull(result);
        assertEquals(result.getId(), categoryDTO.getId());
    }

    @Test(expected = SystemException.class)
    public void shouldNotUpdateCategory() {
        when(repository.findById(id)).thenReturn(null);
        service.updateCategory(categoryDTO);
    }

    @Test
    public void shouldListCategory() {
        when(repository.findAll()).thenReturn(categoryList);
        when(categoryMapper.toDTOList(categoryList)).thenReturn(categoryDTOList);

        List<CategoryDTO> tempDTOList = categoryMapper.toDTOList(categoryList);
        List<CategoryDTO> tempDTOList2 = service.listCategory();

        assertEquals(tempDTOList.get(0).getId(), tempDTOList2.get(0).getId());
    }

    @Test(expected = SystemException.class)
    public void shouldThrowSysExceptionListCategory() {
        service.listCategory();
    }
}