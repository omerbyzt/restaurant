package com.ba.service;

import com.ba.dto.TableCategoryDTO;
import com.ba.mapper.TableCategoryMapper;
import com.ba.repository.TableCategoryRepository;
import com.ba.unused.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableCategoryService {

    @Autowired
    private TableCategoryRepository tableCategoryRepository;

    @Autowired
    private TableRepository tableRepository;

    public List<TableCategoryDTO> listTableCategories() {
        return TableCategoryMapper.INSTANCE.toDTOList(tableCategoryRepository.findAll());
//        return TableCategoryConverter.convertDTOListtoList(tableCategoryRepository.findAll());
    }

    public String addTableCategory(TableCategoryDTO tableCategoryDTO) {
        tableCategoryRepository.save(TableCategoryMapper.INSTANCE.toEntity(tableCategoryDTO));
//        tableCategoryRepository.save(TableCategoryConverter.convertDTOToTableCategory(tableCategoryDTO));
        return "Table Category Added";
    }

    public String deleteTableCategory(Long id) {
        tableCategoryRepository.deleteById(id);
        return "Table Category Deleted";
    }

    public TableCategoryDTO updateTableCategory(TableCategoryDTO tableCategoryDTO) {
        tableCategoryRepository.saveAndFlush( TableCategoryMapper.INSTANCE.toEntity(tableCategoryDTO));
//        tableCategoryRepository.saveAndFlush(TableCategoryConverter.convertDTOToTableCategory(tableCategoryDTO));
        return tableCategoryDTO;
    }

//    public Set<Tables> listTablesById(Long id) {
//        Optional<TableCategory> tables = tableCategoryRepository.findById(id);
//        return tables.get().getTables();
//    }
}
