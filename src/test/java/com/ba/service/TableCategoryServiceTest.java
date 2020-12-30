package com.ba.service;

import com.ba.builder.MediaBuilder;
import com.ba.builder.MediaDTOBuilder;
import com.ba.builder.TableCategoryBuilder;
import com.ba.builder.TableCategoryDTOBuilder;
import com.ba.dto.MediaDTO;
import com.ba.dto.TableCategoryDTO;
import com.ba.entity.Media;
import com.ba.entity.TableCategory;
import com.ba.exception.SystemException;
import com.ba.mapper.MediaMapper;
import com.ba.mapper.TableCategoryMapper;
import com.ba.repository.TableCategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TableCategoryServiceTest {
    @InjectMocks
    private TableCategoryService tableCategoryService;

    @Mock
    private TableCategoryRepository tableCategoryRepository;

    @Mock
    private TableCategoryMapper tableCategoryMapper;

    @Mock
    private MediaMapper mediaMapper;

    private TableCategory tableCategory;
    private TableCategoryDTO tableCategoryDTO;
    private List<TableCategoryDTO> tableCategoryDTOList = new ArrayList<>();
    private List<TableCategory> tableCategoriesList = new ArrayList<>();
    private long id = 1;
    private Media media;
    private MediaDTO mediaDTO;

    @Before
    public void setUp() throws Exception {
        media = new MediaBuilder().id(1L).name("testString").fileContent(null).build();
        mediaDTO = new MediaDTOBuilder().id(1L).name("testString").fileContent(null).build();

        tableCategory = new TableCategoryBuilder().id(1L).name("Bahçe").number(15).media(media).build();
        tableCategoryDTO = new TableCategoryDTOBuilder().id(1L).name("BahçeDTO").number(25).media(mediaDTO).build();

        tableCategoryDTOList.add(tableCategoryDTO);
        tableCategoriesList.add(tableCategory);
    }

    @Test
    public void shouldAddNewTableCategory() {

        Mockito.when(tableCategoryRepository.save(any())).thenReturn(tableCategory);

        String res = tableCategoryService.addTableCategory(tableCategoryDTO);

        assertNotNull(res);
        assertEquals(res,"Table Category Added");
    }

    @Test
    public void shouldDeleteTableCategory() {

        String res = tableCategoryService.deleteTableCategory(id);

        assertEquals(res,"Table Category Deleted");
        verify(tableCategoryRepository,times(1)).deleteById(id);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotDeleteTableCategoryWhenThrownException() {
        doThrow(new RuntimeException("Cant delete here")).when(tableCategoryRepository).deleteById(id);
        tableCategoryService.deleteTableCategory(id);
    }

    @Test
    public void shouldUpdateTableCategory() {
        when(tableCategoryRepository.findById(id)).thenReturn(Optional.of(tableCategory));
        when(tableCategoryRepository.saveAndFlush(tableCategory)).thenReturn(tableCategory);
        when(tableCategoryMapper.toDTO(tableCategory)).thenReturn(tableCategoryDTO);
        when(mediaMapper.toEntity(mediaDTO)).thenReturn(media);

        TableCategoryDTO result = tableCategoryService.updateTableCategory(tableCategoryDTO);

        assertNotNull(result);
        assertEquals(result,tableCategoryDTO);
    }

    @Test(expected = SystemException.class)
    public void shouldThrowSysExceptionWhenTableCategoryNullUpdateTableCategory() {
        when(tableCategoryRepository.findById(id)).thenReturn(null);
        tableCategoryService.updateTableCategory(tableCategoryDTO);
    }

    @Test
    public void shouldListTableCategory() {
        when(tableCategoryRepository.findAll()).thenReturn(tableCategoriesList);
        when(tableCategoryMapper.toDTOList(tableCategoriesList)).thenReturn(tableCategoryDTOList);

        List<TableCategoryDTO> result = tableCategoryService.listTableCategories();

        assertNotNull(result);
        assertEquals(tableCategoryDTOList,result);
    }

    @Test(expected = SystemException.class)
    public void shouldThrowSysExceptionWhenTableCategoryDTONullListTableCategories() {
        when(tableCategoryRepository.findAll()).thenReturn(null);
        tableCategoryService.listTableCategories();
    }
}