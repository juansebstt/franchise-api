package com.franchiseapp.service;

import com.franchiseapp.commons.dtos.BranchDTO;
import com.franchiseapp.commons.dtos.UpdateNameDTO;

/**
 * Service interface for managing branches within a franchise.
 * This interface defines the methods for creating and updating branch information.
 */
public interface BranchService {

    /**
     * Creates a new branch associated with a specific franchise.
     *
     * @param franchiseId The ID of the franchise to which the branch will be linked.
     * @param branch      The BranchDTO containing the details of the branch to be created.
     * @return The created BranchDTO, including the generated ID.
     */
    BranchDTO createBranch(Long franchiseId, BranchDTO branch);  // Pass franchise ID to link it

    /**
     * Updates the name of an existing branch identified by its ID.
     *
     * @param id              The ID of the branch to be updated.
     * @param updateNameDTO   The UpdateNameDTO containing the new name for the branch.
     * @return The updated BranchDTO.
     */
    BranchDTO updateBranchName(Long id, UpdateNameDTO updateNameDTO);

}
