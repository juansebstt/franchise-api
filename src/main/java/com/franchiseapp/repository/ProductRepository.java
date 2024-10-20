package com.franchiseapp.repository;

import com.franchiseapp.commons.entity.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository interface for managing Product entities.
 */
public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    /**
     * Find all products associated with a specific branch by its ID.
     *
     * @param branchId the ID of the branch
     * @return a list of ProductModel associated with the given branch ID
     */
    List<ProductModel> findByBranchId(Long branchId);

    /**
     * Find the product with the highest stock for each branch within a specific franchise.
     *
     * @param franchiseId the ID of the franchise
     * @return a list of ProductModel with the highest stock for each branch in the specified franchise
     */
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
