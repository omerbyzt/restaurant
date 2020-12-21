package com.ba.service;

import com.ba.builder.TableCategoryBuilder;
import com.ba.builder.TableCategoryDTOBuilder;
import com.ba.dto.TableCategoryDTO;
import com.ba.entity.TableCategory;
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

    private TableCategory tableCategory = new TableCategory();
    private TableCategoryDTO tableCategoryDTO = new TableCategoryDTO();
    private List<TableCategoryDTO> tableCategoryDTOList = new ArrayList<>();
    private List<TableCategory> tableCategoriesList = new ArrayList<>();
    private long id = 1;

    @Before
    public void setUp() throws Exception {

        tableCategory = new TableCategoryBuilder().id(1L).name("Bahçe").number(15).build();
        tableCategoryDTO = new TableCategoryDTOBuilder().id(2L).name("BahçeDTO").number(25).build();

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
        String res = tableCategoryService.deleteTableCategory(id);
    }

    @Test
    public void shouldUpdateTableCategory() {

        when(tableCategoryRepository.saveAndFlush(tableCategory)).thenReturn(tableCategory);

        TableCategoryDTO tableCategoryDTO2 = tableCategoryService.updateTableCategory(tableCategoryDTO);

        assertNotNull(tableCategoryDTO2);
        assertEquals(tableCategoryDTO2,tableCategoryDTO);
    }

    @Test
    public void shouldListTableCategory() {

        when(tableCategoryRepository.findAll()).thenReturn(tableCategoriesList);

        List<TableCategoryDTO> tempDTOList = TableCategoryMapper.INSTANCE.toDTOList(tableCategoriesList);
//        List<TableCategoryDTO> tempDTOList = TableCategoryConverter.convertDTOListtoList(tableCategoriesList);
        List<TableCategoryDTO> tempDTOList2 = tableCategoryService.listTableCategories();

        assertEquals(tempDTOList.get(0).getId(),tempDTOList2.get(0).getId());
    }
}