package com.franchiseapp.controller.impl;

import com.franchiseapp.commons.dto.BranchDTO;
import com.franchiseapp.commons.dto.UpdateNameDTO;
import com.franchiseapp.controller.BranchApi;
import com.franchiseapp.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing branch operations in the franchise application.
 * This class implements the BranchApi interface to define the endpoints.
 */
@RestController
public class BranchController implements BranchApi {

    private final BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    /**
     * Creates a new branch for a specified franchise.
     *
     * @param franchiseId the ID of the franchise to which the branch will be added
     * @param branch the branch data transfer object containing branch details
     * @return ResponseEntity containing the created BranchDTO
     */
    @Override
    public ResponseEntity<BranchDTO> createBranch(Long franchiseId, BranchDTO branch) {
        return ResponseEntity.ok(branchService.createBranch(franchiseId, branch));
    }

    /**
     * Updates the name of an existing branch.
     *
     * @param id the ID of the branch to be updated
     * @param updateNameDTO the data transfer object containing the new name
     * @return ResponseEntity containing the updated BranchDTO
     */
    @Override
    public ResponseEntity<BranchDTO> updateBranch(Long id, UpdateNameDTO updateNameDTO) {
        return ResponseEntity.ok(branchService.updateBranchName(id, updateNameDTO));
    }

}
