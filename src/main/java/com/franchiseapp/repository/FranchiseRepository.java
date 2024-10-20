package com.franchiseapp.repository;

import com.franchiseapp.commons.entity.FranchiseModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Franchise entities.
 */
public interface FranchiseRepository extends JpaRepository<FranchiseModel, Long> {
}
