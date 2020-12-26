package com.ba.controller;

import com.ba.dto.TableCategoryDTO;
import com.ba.entity.TableCategory;
import com.ba.exception.BusinessRuleException;
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
    public String addTableCategory(@RequestBody TableCategoryDTO tableCategoryDTO){
        if(tableCategoryDTO == null){
            throw new BusinessRuleException("Table Category cannot be empty...!");
        }
        tableCategoryService.addTableCategory(tableCategoryDTO);
        return "Table Category Added";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTableCategory(@PathVariable Long id){
        if(id == null){
            throw new BusinessRuleException("Id cannot be empty...!");
        }
        tableCategoryService.deleteTableCategory(id);
        return "Table Category Deleted";
    }

    @PutMapping("/update")
    public TableCategoryDTO updateTableCategory(@RequestBody TableCategoryDTO tableCategoryDTO){
        if(tableCategoryDTO == null){
            throw new BusinessRuleException("Table Category cannot be empty...!");
        }
        tableCategoryService.updateTableCategory(tableCategoryDTO);
        return tableCategoryDTO;
    }
}
