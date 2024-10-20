package com.franchiseapp.controller;

import com.franchiseapp.commons.constants.ApiPathConstants;
import com.franchiseapp.commons.dtos.UpdateNameDTO;
import com.franchiseapp.commons.entities.BranchModel;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.BRANCH)
public interface BranchApi {

    @PostMapping("/{franchiseId}")
    @Operation(summary = "Create a new branch for a franchise using the franchise id as a reference")
    ResponseEntity<BranchModel> createBranch(@PathVariable Long franchiseId, @RequestBody BranchModel branchModel);

    @PutMapping("/{id}")
    @Operation(summary = "Update the name of a branch using its id")
    ResponseEntity<BranchModel> updateBranch(@PathVariable Long id, @RequestBody UpdateNameDTO updateNameDTO);

}
