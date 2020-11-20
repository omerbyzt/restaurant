package com.ba.repository;

import com.ba.entity.Orderr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orderr,Long> {
}
