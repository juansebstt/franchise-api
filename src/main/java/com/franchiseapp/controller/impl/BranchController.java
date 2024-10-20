package com.franchiseapp.controller.impl;

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

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @Override
    public ResponseEntity<BranchModel> createBranch(Long franchiseId, BranchModel branchModel) {
        return ResponseEntity.ok(branchService.createBranch(franchiseId, branchModel));
    }

    @Override
    public ResponseEntity<BranchModel> updateBranch(Long id, UpdateNameDTO updateNameDTO) {
        return ResponseEntity.ok(branchService.updateBranchName(id, updateNameDTO));
    }

}
