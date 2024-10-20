package com.franchiseapp.repositories;

import com.franchiseapp.commons.entities.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
}
