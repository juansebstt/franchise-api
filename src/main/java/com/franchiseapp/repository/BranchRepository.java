package com.franchiseapp.repository;

import com.franchiseapp.commons.entity.BranchModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Repository interface for managing Branch entities.
 */
public interface BranchRepository extends JpaRepository<BranchModel, Long> {

    /**
     * Finds all branches associated with a given franchise ID.
     *
     * @param franchiseId the ID of the franchise to find branches for
     * @return a list of BranchModel entities associated with the specified franchise ID
     */
    List<BranchModel> findByFranchiseId(Long franchiseId);


}
