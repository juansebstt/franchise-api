package com.franchiseapp.controller;


import com.franchiseapp.commons.constants.ApiPathConstants;
import com.franchiseapp.commons.dtos.FranchiseDTO;
import com.franchiseapp.commons.dtos.UpdateNameDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.FRANCHISE)
public interface FranchiseApi {

    /**
     * Creates a new franchise.
     *
     * @param franchiseModel the data transfer object containing franchise details
     * @return ResponseEntity containing the created FranchiseDTO
     */
    @PostMapping
    @Operation(summary = "Create a new franchise")
    ResponseEntity<FranchiseDTO> createFranchise(@RequestBody FranchiseDTO franchiseModel);

    /**
     * Updates the name of an existing franchise.
     *
     * @param id the ID of the franchise to be updated
     * @param updateNameDTO the data transfer object containing the new name
     * @return ResponseEntity containing the updated FranchiseDTO
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update the name of a franchise using its id")
    ResponseEntity<FranchiseDTO> updateFranchise(@PathVariable Long id, @RequestBody UpdateNameDTO updateNameDTO);

    /**
     * Retrieves all franchises.
     *
     * @return ResponseEntity containing a list of FranchiseDTOs
     */
    @GetMapping
    @Operation(summary = "Get all franchises")
    ResponseEntity<List<FranchiseDTO>> getAllFranchises();

}
