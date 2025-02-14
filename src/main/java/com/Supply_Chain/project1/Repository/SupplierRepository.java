package com.Supply_Chain.project1.Repository;


import com.Supply_Chain.project1.Model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    // Custom query using JPQL
    @Query("SELECT s FROM Supplier s JOIN s.products p WHERE p.id = :productId AND p.stock >= :stock")
    List<Supplier> findByProductIdAndStockGreaterThanEqual(Long productId, int stock);
}
