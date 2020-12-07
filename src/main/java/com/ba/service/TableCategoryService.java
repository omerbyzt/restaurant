package com.ba.service;

import com.ba.converter.TableCategoryConverter;
import com.ba.dto.TableCategoryDTO;
import com.ba.entity.TableCategory;
import com.ba.unused.Tables;
import com.ba.repository.TableCategoryRepository;
import com.ba.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TableCategoryService {

    @Autowired
    TableCategoryRepository tableCategoryRepository;

    @Autowired
    TableRepository tableRepository;

    public List<TableCategoryDTO> listTableCategories() {
        return TableCategoryConverter.convertDTOListtoList(tableCategoryRepository.findAll());
    }

    public String addTableCategory(TableCategoryDTO tableCategoryDTO) {
        tableCategoryRepository.save(TableCategoryConverter.convertDTOToTableCategory(tableCategoryDTO));
        return "Table Category Added";
    }

    public String deleteTableCategory(Long id) {
        tableCategoryRepository.deleteById(id);
        return "Table Category Deleted";
    }

    public TableCategoryDTO updateTableCategory(TableCategoryDTO tableCategoryDTO) {
        tableCategoryRepository.saveAndFlush(TableCategoryConverter.convertDTOToTableCategory(tableCategoryDTO));
        return tableCategoryDTO;
    }

//    public Set<Tables> listTablesById(Long id) {
//        Optional<TableCategory> tables = tableCategoryRepository.findById(id);
//        return tables.get().getTables();
//    }
}
