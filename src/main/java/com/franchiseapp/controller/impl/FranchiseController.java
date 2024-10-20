package com.franchiseapp.controller.impl;

import com.franchiseapp.commons.dtos.UpdateNameDTO;
import com.franchiseapp.commons.entities.FranchiseModel;
import com.franchiseapp.controller.FranchiseApi;
import com.franchiseapp.service.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FranchiseController implements FranchiseApi {

    private final FranchiseService franchiseService;

    @Autowired
    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }

    @Override
    public ResponseEntity<FranchiseModel> createFranchise(FranchiseModel franchise) {
        return ResponseEntity.ok(franchiseService.createFranchise(franchise));
    }

    @Override
    public ResponseEntity<FranchiseModel> updateFranchise(Long id, UpdateNameDTO updateNameDTO) {
        return ResponseEntity.ok(franchiseService.updateFranchiseName(id, updateNameDTO));
    }

    @Override
    public ResponseEntity<List<FranchiseModel>> getAllFranchises() {
        return ResponseEntity.ok(franchiseService.getAllFranchises());
    }
}
