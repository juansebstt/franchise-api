package com.franchiseapp.controller;

import com.franchiseapp.commons.constants.ApiPathConstants;
import com.franchiseapp.commons.dtos.UpdateNameDTO;
import com.franchiseapp.commons.entities.ProductModel;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.PRODUCT)
public interface ProductApi {

    @PostMapping("/{branchId}")
    @Operation(summary = "Create a new product for a branch based on its id")
    ResponseEntity<ProductModel> createProduct(@PathVariable Long branchId, @RequestBody ProductModel productModel);

    @PutMapping("/stock/{productId}")
    @Operation(summary = "Update the stock of a product")
    ResponseEntity<ProductModel> updateProductStock(@PathVariable Long productId, @RequestParam Integer stock);

    @PutMapping("/name/{productId}")
    @Operation(summary = "Update the name of a product")
    ResponseEntity<ProductModel> updateProductName(@PathVariable Long productId, @RequestBody UpdateNameDTO updateNameDTO);

    @DeleteMapping("/{productId}")
    @Operation(summary = "Delete a product")
    ResponseEntity<Void> deleteProduct(@PathVariable Long productId);

    @GetMapping("/most-stock/{franchiseId}")
    @Operation(summary = "Get products with the most stock for a specific franchise")
    ResponseEntity<List<ProductModel>> getProductsWithMostStockByFranchise(@PathVariable Long franchiseId);

}