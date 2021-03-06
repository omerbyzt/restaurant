package com.ba.service;

import com.ba.dto.TableCategoryDTO;
import com.ba.entity.TableCategory;
import com.ba.exception.SystemException;
import com.ba.helper.UpdateHelper;
import com.ba.mapper.MediaMapper;
import com.ba.mapper.TableCategoryMapper;
import com.ba.repository.TableCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TableCategoryService {

    @Autowired
    private TableCategoryRepository tableCategoryRepository;

    @Autowired
    private TableCategoryMapper tableCategoryMapper;

    @Autowired
    private MediaMapper mediaMapper;

    public List<TableCategoryDTO> listTableCategories() {
        List<TableCategory> categoryDTOList = tableCategoryRepository.findAll();
        if(categoryDTOList == null){
            throw new SystemException("Table categories not found...!");
        }
        return tableCategoryMapper.toDTOList(categoryDTOList);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String addTableCategory(TableCategoryDTO tableCategoryDTO) {
        tableCategoryRepository.save(tableCategoryMapper.toEntity(tableCategoryDTO));
        return "Table Category Added";
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String deleteTableCategory(Long id) {
        tableCategoryRepository.deleteById(id);
        return "Table Category Deleted";
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public TableCategoryDTO updateTableCategory(TableCategoryDTO tableCategoryDTO) {
        Optional<TableCategory> tableCategory = tableCategoryRepository.findById(tableCategoryDTO.getId());
        if(tableCategory == null){
            throw new SystemException("Table category not found");
        }

        UpdateHelper.tableCategorySetCheck(tableCategoryDTO, tableCategory, mediaMapper);

        tableCategoryRepository.saveAndFlush(tableCategory.get());

        return tableCategoryMapper.toDTO(tableCategory.get());
    }
}
