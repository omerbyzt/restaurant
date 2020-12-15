package com.ba.unused;

import com.ba.entity.TableCategory;
import com.ba.repository.TableCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableService {

    @Autowired
    TableRepository tableRepository;

    @Autowired
    TableCategoryRepository tableCategoryRepository;

    public List<Tables> listAllTables(){
        return tableRepository.findAll();
    }

    public List<Tables> addTable(Tables table,Long id){
        Optional<TableCategory> tableCategory = tableCategoryRepository.findById(id);
       // tableCategory.get().getTables().add(table);
        tableCategoryRepository.save(tableCategory.get());
        return listAllTables();
    }

    public List<Tables> deleteTable(Long id){
        tableRepository.deleteById(id);
        return listAllTables();
    }

    public List<Tables> updateTable(Tables table){
        tableRepository.saveAndFlush(table);
        return listAllTables();
    }
}
