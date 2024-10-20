package com.franchiseapp.controller.impl;

import com.franchiseapp.commons.dtos.BranchDTO;
import com.franchiseapp.commons.dtos.UpdateNameDTO;
import com.franchiseapp.commons.entities.BranchModel;
import com.franchiseapp.controller.BranchApi;
import com.franchiseapp.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BranchController implements BranchApi {

    private final BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @Override
    public ResponseEntity<BranchDTO> createBranch(Long franchiseId, BranchDTO branch) {
        return ResponseEntity.ok(branchService.createBranch(franchiseId, branch));
    }

    @Override
    public ResponseEntity<BranchDTO> updateBranch(Long id, UpdateNameDTO updateNameDTO) {
        return ResponseEntity.ok(branchService.updateBranchName(id, updateNameDTO));
    }

}
