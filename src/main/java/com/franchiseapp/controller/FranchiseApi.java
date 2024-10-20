package com.franchiseapp.controller;


import com.franchiseapp.commons.constants.ApiPathConstants;
import com.franchiseapp.commons.dtos.UpdateNameDTO;
import com.franchiseapp.commons.entities.FranchiseModel;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.FRANCHISE)
public interface FranchiseApi {

    @PostMapping
    @Operation(summary = "Create a new franchise")
    ResponseEntity<FranchiseModel> createFranchise(@RequestBody FranchiseModel franchiseModel);

    @PutMapping("/{id}")
    @Operation(summary = "Update the name of a franchise using its id")
    ResponseEntity<FranchiseModel> updateFranchise(@PathVariable Long id, @RequestBody UpdateNameDTO updateNameDTO);

    @GetMapping
    @Operation(summary = "Get all franchises")
    ResponseEntity<List<FranchiseModel>> getAllFranchises();

}
