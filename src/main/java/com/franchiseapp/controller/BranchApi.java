package com.franchiseapp.controller;

import com.franchiseapp.commons.constants.ApiPathConstants;
import com.franchiseapp.commons.dtos.BranchDTO;
import com.franchiseapp.commons.dtos.UpdateNameDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.BRANCH)
public interface BranchApi {

    /**
     * Creates a new branch for a franchise.
     *
     * @param franchiseId the ID of the franchise to create the branch for
     * @param branch the branch data transfer object containing branch details
     * @return ResponseEntity containing the created BranchDTO
     */
    @PostMapping("/{franchiseId}")
    @Operation(summary = "Create a new branch for a franchise using the franchise id as a reference")
    ResponseEntity<BranchDTO> createBranch(@PathVariable Long franchiseId, @RequestBody BranchDTO branch);

    /**
     * Updates the name of an existing branch.
     *
     * @param id the ID of the branch to be updated
     * @param updateNameDTO the data transfer object containing the new name
     * @return ResponseEntity containing the updated BranchDTO
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update the name of a branch using its id")
    ResponseEntity<BranchDTO> updateBranch(@PathVariable Long id, @RequestBody UpdateNameDTO updateNameDTO);

}
