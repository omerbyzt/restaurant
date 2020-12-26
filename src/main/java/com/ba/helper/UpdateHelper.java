package com.ba.helper;

import com.ba.dto.CategoryDTO;
import com.ba.dto.CustomerDTO;
import com.ba.dto.RoleDTO;
import com.ba.dto.TableCategoryDTO;
import com.ba.entity.*;
import com.ba.mapper.MediaMapper;

import java.util.Optional;

public class UpdateHelper {

    public static void categorySetCheck(CategoryDTO categoryDTO, Optional<Category> tempCategory) {
        if(!tempCategory.get().getName().equals(categoryDTO.getName())){
            tempCategory.get().setName(categoryDTO.getName());
        }
        if(!tempCategory.get().getDescription().equals(categoryDTO.getDescription())){
            tempCategory.get().setDescription(categoryDTO.getDescription());
        }
        Media tempMedia = MediaMapper.INSTANCE.toEntity(categoryDTO.getMediaDTO());
        if(!tempCategory.get().getMedia().equals(tempMedia)){
            tempCategory.get().setMedia(tempMedia);
        }
    }

    public static void customerSetCheck(CustomerDTO customerDTO, Optional<Customer> customer) {
        if(!customer.get().getName().equals(customerDTO.getName())){
            customer.get().setName(customerDTO.getName());
        }
        if(!customer.get().getSurname().equals(customerDTO.getSurname())){
            customer.get().setSurname(customerDTO.getSurname());
        }
        if(!customer.get().getAddress().equals(customerDTO.getAddress())){
            customer.get().setAddress(customerDTO.getAddress());
        }
        if(!customer.get().getPhoneNumber().equals(customerDTO.getPhoneNumber())){
            customer.get().setPhoneNumber(customerDTO.getPhoneNumber());
        }
    }

    public static void tableCategorySetCheck(TableCategoryDTO tableCategoryDTO, Optional<TableCategory> tableCategory) {
        if(!tableCategory.get().getName().equals(tableCategoryDTO.getName())){
            tableCategory.get().setName(tableCategoryDTO.getName());
        }
        if(tableCategory.get().getNumber() != (tableCategoryDTO.getNumber())){
            tableCategory.get().setNumber(tableCategoryDTO.getNumber());
        }
    }
    public static void roleSetCheck(RoleDTO roleDTO, Optional<Role> role) {
        if(role.get().getName().equals(roleDTO.getName())){
            role.get().setName(roleDTO.getName());
        }
    }
}