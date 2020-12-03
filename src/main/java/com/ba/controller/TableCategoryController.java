package com.ba.controller;

import com.ba.dto.TableCategoryDTO;
import com.ba.entity.TableCategory;
import com.ba.unused.Tables;
import com.ba.service.TableCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/table-category")
public class TableCategoryController {

    @Autowired
    TableCategoryService tableCategoryService;

    @GetMapping("/listall")
    public List<TableCategoryDTO> listTableCategories(){
        return tableCategoryService.listTableCategories();
    }

    @PostMapping("/add")
    public List<TableCategoryDTO> addTableCategory(@RequestBody TableCategoryDTO tableCategoryDTO){
        return tableCategoryService.addTableCategory(tableCategoryDTO);
    }

    @DeleteMapping("/delete/{id}")
    public List<TableCategoryDTO> deleteTableCategory(@PathVariable Long id){
        return tableCategoryService.deleteTableCategory(id);
    }

    @PutMapping("/update")
    public List<TableCategoryDTO> updateTableCategory(@RequestBody TableCategoryDTO tableCategoryDTO){
        return tableCategoryService.updateTableCategory(tableCategoryDTO);
    }

//    @GetMapping("/list-table/{id}")
//    public Set<Tables> listTablesById(@PathVariable Long id){
//        return tableCategoryService.listTablesById(id);
//    }

}
