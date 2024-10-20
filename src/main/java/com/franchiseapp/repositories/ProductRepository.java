package com.franchiseapp.repositories;

import com.franchiseapp.commons.entities.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    List<ProductModel> findByBranchId(Long branchId);

    @Query("""
        SELECT p
        FROM ProductModel p
        JOIN p.branch b
        WHERE b.franchise.id = :franchiseId
        AND p.stock = (
            SELECT MAX(p2.stock)
            FROM ProductModel p2
            WHERE p2.branch.id = p.branch.id
        )
    """)

    List<ProductModel> findTopProductByStockForEachBranchInFranchise(Long franchiseId);
}
