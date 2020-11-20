package com.ba.repository;

import com.ba.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("Select DISTINCT productCategory From Product")
    public List<String> listAllCategories();

    @Query("Select p From Product p Where p.productCategory=:category")
    public List<Product>listSelectedCategory(String category);
}
