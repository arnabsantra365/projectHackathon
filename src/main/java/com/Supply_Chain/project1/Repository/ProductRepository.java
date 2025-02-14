package com.Supply_Chain.project1.Repository;

import com.Supply_Chain.project1.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findBySupplierId(Long supplierId);
}
