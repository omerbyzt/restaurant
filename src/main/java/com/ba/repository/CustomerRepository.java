package com.ba.repository;

import com.ba.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Slice<Customer> findBy(Pageable pageable);
    Page<Customer> findAll(Pageable pageable);
}
