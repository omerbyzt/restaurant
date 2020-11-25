package com.ba.controller;

import com.ba.entity.TableCategory;
import com.ba.entity.Tables;
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
    public List<TableCategory> listTableCategories(){
        return tableCategoryService.listTableCategories();
    }

    @PostMapping("/add")
    public List<TableCategory> addTableCategory(@RequestBody TableCategory tableCategory){
        return tableCategoryService.addTableCategory(tableCategory);
    }

    @DeleteMapping("/delete/{id}")
    public List<TableCategory> deleteTableCategory(@PathVariable Long id){
        return tableCategoryService.deleteTableCategory(id);
    }

    @PutMapping("/update")
    public List<TableCategory> updateTableCategory(@RequestBody TableCategory tableCategory){
        return tableCategoryService.updateTableCategory(tableCategory);
    }

    @GetMapping("/list-table/{id}")
    public Set<Tables> listTablesById(@PathVariable Long id){
        return tableCategoryService.listTablesById(id);
    }

}
