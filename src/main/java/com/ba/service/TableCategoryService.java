package com.ba.service;

import com.ba.dto.TableCategoryDTO;
import com.ba.entity.TableCategory;
import com.ba.exception.SystemException;
import com.ba.helper.UpdateHelper;
import com.ba.mapper.TableCategoryMapper;
import com.ba.repository.TableCategoryRepository;
import com.mysql.cj.xdevapi.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableCategoryService {

    @Autowired
    private TableCategoryRepository tableCategoryRepository;

    public List<TableCategoryDTO> listTableCategories() {
        List<TableCategory> categoryDTOList = tableCategoryRepository.findAll();
        if(categoryDTOList.isEmpty()){
            throw new SystemException("Table categories not found...!");
        }
        return TableCategoryMapper.INSTANCE.toDTOList(categoryDTOList);
    }

    public String addTableCategory(TableCategoryDTO tableCategoryDTO) {
        tableCategoryRepository.save(TableCategoryMapper.INSTANCE.toEntity(tableCategoryDTO));
        return "Table Category Added";
    }

    public String deleteTableCategory(Long id) {
        tableCategoryRepository.deleteById(id);
        return "Table Category Deleted";
    }

    public TableCategoryDTO updateTableCategory(TableCategoryDTO tableCategoryDTO) {
        Optional<TableCategory> tableCategory = tableCategoryRepository.findById(tableCategoryDTO.getId());
        if(tableCategory.isEmpty()){
            throw new SystemException("Table category not found");
        }

        UpdateHelper.tableCategorySetCheck(tableCategoryDTO, tableCategory);

        tableCategoryRepository.saveAndFlush(tableCategory.get());

        return TableCategoryMapper.INSTANCE.toDTO(tableCategory.get());
    }

}
