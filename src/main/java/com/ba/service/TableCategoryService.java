package com.ba.service;

import com.ba.entity.Category;
import com.ba.entity.TableCategory;
import com.ba.entity.Tables;
import com.ba.repository.TableCategoryRepository;
import com.ba.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TableCategoryService {

    @Autowired
    TableCategoryRepository tableCategoryRepository;

    @Autowired
    TableRepository tableRepository;

    public List<TableCategory> listTableCategories(){
        return tableCategoryRepository.findAll();
    }

    public List<TableCategory> addTableCategory(TableCategory tableCategory){
        tableCategoryRepository.save(tableCategory);
        return listTableCategories();
    }

    public List<TableCategory> deleteTableCategory(Long id){
        tableCategoryRepository.deleteById(id);
        return listTableCategories();
    }

    public List<TableCategory> updateTableCategory(TableCategory tableCategory){
        tableCategoryRepository.saveAndFlush(tableCategory);
        return listTableCategories();
    }

    public Set<Tables> listTablesById(Long id){
        Optional<TableCategory> tables = tableCategoryRepository.findById(id);
        return tables.get().getTables();
    }
}
