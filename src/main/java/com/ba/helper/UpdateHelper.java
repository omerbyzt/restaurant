package com.ba.helper;

import com.ba.dto.*;
import com.ba.entity.*;
import com.ba.exception.SystemException;
import com.ba.mapper.CategoryMapper;
import com.ba.mapper.MediaMapper;
import com.ba.mapper.RoleMapper;
import com.ba.repository.CategoryRepository;

import java.util.Optional;

public class UpdateHelper {

    public static void categorySetCheck(CategoryDTO categoryDTO, Optional<Category> tempCategory) {
        if (!tempCategory.get().getName().equals(categoryDTO.getName())) {
            tempCategory.get().setName(categoryDTO.getName());
        }
        if (!tempCategory.get().getDescription().equals(categoryDTO.getDescription())) {
            tempCategory.get().setDescription(categoryDTO.getDescription());
        }
        Media tempMedia = MediaMapper.INSTANCE.toEntity(categoryDTO.getMediaDTO());
        if (!tempCategory.get().getMedia().equals(tempMedia)) {
            tempCategory.get().setMedia(tempMedia);
        }
    }

    public static void customerSetCheck(CustomerDTO customerDTO, Optional<Customer> customer) {
        if (!customer.get().getName().equals(customerDTO.getName())) {
            customer.get().setName(customerDTO.getName());
        }
        if (!customer.get().getSurname().equals(customerDTO.getSurname())) {
            customer.get().setSurname(customerDTO.getSurname());
        }
        if (!customer.get().getAddress().equals(customerDTO.getAddress())) {
            customer.get().setAddress(customerDTO.getAddress());
        }
        if (!customer.get().getPhoneNumber().equals(customerDTO.getPhoneNumber())) {
            customer.get().setPhoneNumber(customerDTO.getPhoneNumber());
        }
        Media tempMedia = MediaMapper.INSTANCE.toEntity((customerDTO.getMedia()));
        if (!customer.get().getMedia().equals(customerDTO.getMedia())) {
            customer.get().setMedia(tempMedia);
        }
    }

    public static void tableCategorySetCheck(TableCategoryDTO tableCategoryDTO, Optional<TableCategory> tableCategory) {
        if (!tableCategory.get().getName().equals(tableCategoryDTO.getName())) {
            tableCategory.get().setName(tableCategoryDTO.getName());
        }
        if (tableCategory.get().getNumber() != (tableCategoryDTO.getNumber())) {
            tableCategory.get().setNumber(tableCategoryDTO.getNumber());
        }
        Media tempMedia = MediaMapper.INSTANCE.toEntity(tableCategoryDTO.getMedia());
        if (!tableCategory.get().getMedia().equals(tempMedia)) {
            tableCategory.get().setMedia(tempMedia);
        }
    }

    public static void roleSetCheck(RoleDTO roleDTO, Optional<Role> role) {
        if (role.get().getName().equals(roleDTO.getName())) {
            role.get().setName(roleDTO.getName());
        }
    }

    public static void waiterSetCheck(WaiterDTO waiterDTO, Optional<Waiter> waiter) {
        if (!waiter.get().getName().equals(waiterDTO.getName())) {
            waiter.get().setName(waiterDTO.getName());
        }
        if (!waiter.get().getAddress().equals(waiterDTO.getAddress())) {
            waiter.get().setAddress(waiterDTO.getAddress());
        }
        if (!waiter.get().getMail().equals(waiterDTO.getMail())) {
            waiter.get().setMail(waiterDTO.getMail());
        }
        if (!waiter.get().getPhoneNumber().equals(waiterDTO.getPhoneNumber())) {
            waiter.get().setPhoneNumber(waiterDTO.getPhoneNumber());
        }
        if (!waiter.get().getSalary().equals(waiterDTO.getSalary())) {
            waiter.get().setSalary(waiterDTO.getSalary());
        }
    }

    public static void userSetCheck(UserDTO userDTO, User user) {
        if (!user.getUsername().equals(userDTO.getUsername())) {
            user.setUsername(userDTO.getUsername());
        }
        if (!user.getEmail().equals(userDTO.getEmail())) {
            user.setEmail(userDTO.getEmail());
        }
        if (!user.getPassword().equals(userDTO.getPassword())) {
            user.setPassword(userDTO.getPassword());
        }
        removeRedundantRole(userDTO, user);
        addNotExistRole(userDTO, user);
    }

    public static void addNotExistRole(UserDTO userDTO, User user) {
        //lambda for
        for (int i = 0; i < userDTO.getRoles().size(); i++) {
            boolean hasRole = false;
            for (int j = 0; j < user.getRoles().size(); j++) {
                if (userDTO.getRoles().get(i).getId().equals(user.getRoles().get(j).getId())) {
                    hasRole = true;
                }
            }
            if (!hasRole) {
                user.getRoles().add(RoleMapper.INSTANCE.toEntity(userDTO.getRoles().get(i)));
            }
        }
    }

    public static void removeRedundantRole(UserDTO userDTO, User user) {
        for (int i = 0; i < user.getRoles().size(); i++) {
            boolean hasRole = false;
            for (int j = 0; j < userDTO.getRoles().size(); j++) {
                if (user.getRoles().get(i).getId().equals(userDTO.getRoles().get(j).getId())) {
                    hasRole = true;
                }
            }
            if (!hasRole) {
                user.getRoles().remove(i);
            }
        }
    }

    public static void productSetCheck(ProductDTO productDTO, Optional<Product> product, CategoryRepository categoryRepository) {
        if (!product.get().getProductName().equals(productDTO.getProductName())) {
            product.get().setProductName(productDTO.getProductName());
        }
        if (!product.get().getProductDesc().equals(productDTO.getProductDesc())) {
            product.get().setProductDesc(productDTO.getProductDesc());
        }
        if (!product.get().getProductPrice().equals(productDTO.getProductPrice())) {
            product.get().setProductPrice(productDTO.getProductPrice());
        }
        if (!product.get().getProductCategory().equals(productDTO.getProductCategory())) {
            product.get().setProductCategory(productDTO.getProductCategory());
        }
        if (!product.get().getMedia().getId().equals(productDTO.getMediaDTO().getId())) {
            product.get().setMedia(MediaMapper.INSTANCE.toEntity(productDTO.getMediaDTO()));
        }

        removeCategory(productDTO, product, categoryRepository);
        addCategory(productDTO, product, categoryRepository);

    }

    public static void addCategory(ProductDTO productDTO, Optional<Product> product, CategoryRepository categoryRepository) {
        for (int i = 0; i < productDTO.getCategories().size(); i++) {
            boolean hasCategory = false;
            for (int j = 0; j < product.get().getCategories().size(); j++) {
                if (productDTO.getCategories().get(i).getId().equals(product.get().getCategories().get(j).getId())) {
                    hasCategory = true;
                }
            }
            if (!hasCategory) {
                product.get().getCategories().add(CategoryMapper.INSTANCE.toEntity(productDTO.getCategories().get(i)));
                Category category = categoryRepository.findById(productDTO.getCategories().get(i).getId()).get();
                category.getProducts().add(product.get());
            }
        }
    }

    public static void removeCategory(ProductDTO productDTO, Optional<Product> product, CategoryRepository categoryRepository) {
        for (int i = 0; i < product.get().getCategories().size(); i++) {
            boolean hasCategory = false;
            for (int j = 0; j < productDTO.getCategories().size(); j++) {
                if (product.get().getCategories().get(i).getId().equals(productDTO.getCategories().get(j).getId())) {
                    hasCategory = true;
                }
            }
            if (!hasCategory) {
                product.get().getCategories().get(i).getProducts().remove(product.get());
            }
        }
    }
}
