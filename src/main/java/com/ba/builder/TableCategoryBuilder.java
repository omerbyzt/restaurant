package com.ba.builder;

import com.ba.entity.TableCategory;

public class TableCategoryBuilder extends Builder{

    private String name;
    private int number;

    public TableCategoryBuilder id(Long id){
        this.setId(id);
        return this;
    }

    public TableCategoryBuilder name(String name){
        this.name = name;
        return this;
    }

    public TableCategoryBuilder number(int number){
        this.number = number;
        return this;
    }

    @Override
    public TableCategory build() {
        TableCategory tableCategory = new TableCategory();

        tableCategory.setNumber(this.number);
        tableCategory.setName(this.name);
        tableCategory.setId(this.getId());

        return tableCategory;
    }
}
