package com.ba.repository;

import com.ba.entity.Category;
import com.ba.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Override
    Page<Product> findAll(Pageable pageable);

    Slice<Product> findProductByCategoriesId(Pageable pageable, Long id);
}
