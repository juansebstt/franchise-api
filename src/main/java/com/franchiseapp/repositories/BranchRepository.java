package com.franchiseapp.repositories;

import com.franchiseapp.commons.entities.BranchModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<BranchModel, Long> {

    List<BranchModel> findByFranchiseId(Long franchiseId);


}
