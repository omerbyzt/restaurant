package com.ba.controller;

import com.ba.builder.TableCategoryBuilder;
import com.ba.builder.TableCategoryDTOBuilder;
import com.ba.dto.TableCategoryDTO;
import com.ba.entity.TableCategory;
import com.ba.exception.BusinessRuleException;
import com.ba.service.TableCategoryService;
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
public class TableCategoryControllerTest {

    @InjectMocks
    private TableCategoryController controller;

    @Mock
    private TableCategoryService service;

    private TableCategory tableCategory = new TableCategory();
    private TableCategoryDTO tableCategoryDTO = new TableCategoryDTO();
    private List<TableCategoryDTO> tableCategoryDTOList = new ArrayList<>();
    private List<TableCategory> tableCategoriesList = new ArrayList<>();
    private Long id = 111L;

    @Before
    public void setUp() throws Exception {

        tableCategory = new TableCategoryBuilder().id(1L).name("Bahçe").number(15).build();
        tableCategoryDTO = new TableCategoryDTOBuilder().id(2L).name("BahçeDTO").number(25).build();

        tableCategoryDTOList.add(tableCategoryDTO);
        tableCategoriesList.add(tableCategory);
    }

    @Test
    public void shouldVerifyListTableCategories() {
        when(service.listTableCategories()).thenReturn(tableCategoryDTOList);
        List<TableCategoryDTO> tempDTOList = controller.listTableCategories();
        assertEquals(tempDTOList, tableCategoryDTOList);
    }

    @Test
    public void shouldVerifyAddTableCategory() {
        when(service.addTableCategory(tableCategoryDTO)).thenReturn("Table Category Added");
        String res = controller.addTableCategory(tableCategoryDTO);
        assertEquals(res, "Table Category Added");
    }

    @Test
    public void shouldVerifyDeleteTableCategory() {
        when(service.deleteTableCategory(id)).thenReturn("Table Category Deleted");
        String res = controller.deleteTableCategory(id);
        assertEquals(res, "Table Category Deleted");
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotDeleteTableCategory() {
        when(service.deleteTableCategory(id)).thenReturn("Table Category Deleted");
        String res = controller.deleteTableCategory(null);
    }

    @Test
    public void shouldVerifyUpdateTableCategory() {
        when(service.updateTableCategory(tableCategoryDTO)).thenReturn(tableCategoryDTO);
        TableCategoryDTO tempDTO = controller.updateTableCategory(tableCategoryDTO);
        assertEquals(tempDTO, tableCategoryDTO);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotUpdateTableCategory() {
        tableCategoryDTO.setId(0);
        when(service.updateTableCategory(tableCategoryDTO)).thenReturn(tableCategoryDTO);
        controller.updateTableCategory(tableCategoryDTO);
    }
}

