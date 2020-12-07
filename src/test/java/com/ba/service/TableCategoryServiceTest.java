package com.ba.service;

import com.ba.converter.TableCategoryConverter;
import com.ba.dto.TableCategoryDTO;
import com.ba.entity.TableCategory;
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

    @Before
    public void setUp() throws Exception {
        tableCategory.setId(1);
        tableCategory.setName("Bahçe");
        tableCategory.setNumber(15);

        tableCategoryDTO.setId(2);
        tableCategoryDTO.setName("BahçeDTO");
        tableCategoryDTO.setNumber(25);

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
        long id = 111;

        String res = tableCategoryService.deleteTableCategory(id);

        assertEquals(res,"Table Category Deleted");
        verify(tableCategoryRepository,times(1)).deleteById(id);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotDeleteTableCategoryWhenThrownException() {
        long id = 111;

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

        List<TableCategoryDTO> tempDTOList = TableCategoryConverter.convertDTOListtoList(tableCategoriesList);
        List<TableCategoryDTO> tempDTOList2 = tableCategoryService.listTableCategories();

        assertEquals(tempDTOList.get(0).getId(),tempDTOList2.get(0).getId());
    }
}