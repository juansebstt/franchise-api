package com.franchiseapp.repositories;

import com.franchiseapp.commons.entities.FranchiseModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Franchise entities.
 */
public interface FranchiseRepository extends JpaRepository<FranchiseModel, Long> {
}
