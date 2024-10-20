package com.franchiseapp.repositories;

import com.franchiseapp.commons.entities.BranchModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<BranchModel, Long> {
}
