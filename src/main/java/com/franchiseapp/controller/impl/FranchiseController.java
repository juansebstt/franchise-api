package com.franchiseapp.controller.impl;

import com.franchiseapp.commons.dtos.FranchiseDTO;
import com.franchiseapp.commons.dtos.UpdateNameDTO;
import com.franchiseapp.controller.FranchiseApi;
import com.franchiseapp.service.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for managing franchise operations in the franchise application.
 * This class implements the FranchiseApi interface to define the endpoints.
 */
@RestController
public class FranchiseController implements FranchiseApi {

    private final FranchiseService franchiseService;

    @Autowired
    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }

    /**
     * Creates a new franchise.
     *
     * @param franchise the franchise data transfer object containing franchise details
     * @return ResponseEntity containing the created FranchiseDTO
     */
    @Override
    public ResponseEntity<FranchiseDTO> createFranchise(FranchiseDTO franchise) {
        return ResponseEntity.ok(franchiseService.createFranchise(franchise));
    }

    /**
     * Updates the name of an existing franchise.
     *
     * @param id the ID of the franchise to be updated
     * @param updateNameDTO the data transfer object containing the new name
     * @return ResponseEntity containing the updated FranchiseDTO
     */
    @Override
    public ResponseEntity<FranchiseDTO> updateFranchise(Long id, UpdateNameDTO updateNameDTO) {
        return ResponseEntity.ok(franchiseService.updateFranchiseName(id, updateNameDTO));
    }

    /**
     * Retrieves all franchises.
     *
     * @return ResponseEntity containing a list of FranchiseDTOs
     */
    @Override
    public ResponseEntity<List<FranchiseDTO>> getAllFranchises() {
        return ResponseEntity.ok(franchiseService.getAllFranchises());
    }
}
