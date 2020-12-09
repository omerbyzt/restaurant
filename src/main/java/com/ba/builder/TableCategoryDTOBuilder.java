package com.ba.builder;

import com.ba.dto.TableCategoryDTO;

public class TableCategoryDTOBuilder extends Builder{

    private String name;
    private int number;

    public TableCategoryDTOBuilder id(Long id){
        this.setId(id);
        return this;
    }

    public TableCategoryDTOBuilder name(String name){
        this.name = name;
        return this;
    }

    public TableCategoryDTOBuilder number(int number){
        this.number = number;
        return this;
    }

    @Override
    public TableCategoryDTO build() {
        TableCategoryDTO tableCategoryDTO = new TableCategoryDTO();

        tableCategoryDTO.setNumber(this.number);
        tableCategoryDTO.setName(this.name);
        tableCategoryDTO.setId(this.getId());

        return tableCategoryDTO;
    }
}
