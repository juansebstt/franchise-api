package com.franchiseapp.repositories;

import com.franchiseapp.commons.entities.FranchiseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRepository extends JpaRepository<FranchiseModel, Long> {
}
