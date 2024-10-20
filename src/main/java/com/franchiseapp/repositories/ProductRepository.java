package com.franchiseapp.repositories;

import com.franchiseapp.commons.entities.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    List<ProductModel> findByBranchId(Long branchId);

}
