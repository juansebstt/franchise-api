package com.franchiseapp.controller;

import com.franchiseapp.commons.constants.ApiPathConstants;
import com.franchiseapp.commons.dtos.ProductDTO;
import com.franchiseapp.commons.dtos.UpdateNameDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.PRODUCT)
public interface ProductApi {

    /**
     * Creates a new product for a specific branch.
     *
     * @param branchId the ID of the branch where the product will be created
     * @param product the data transfer object containing product details
     * @return ResponseEntity containing the created ProductDTO
     */
    @PostMapping("/{branchId}")
    @Operation(summary = "Create a new product for a branch based on its id")
    ResponseEntity<ProductDTO> createProduct(@PathVariable Long branchId, @RequestBody ProductDTO product);

    /**
     * Updates the stock of an existing product.
     *
     * @param productId the ID of the product to update
     * @param stock the new stock quantity
     * @return ResponseEntity containing the updated ProductDTO
     */
    @PutMapping("/stock/{productId}")
    @Operation(summary = "Update the stock of a product")
    ResponseEntity<ProductDTO> updateProductStock(@PathVariable Long productId, @RequestParam Integer stock);

    /**
     * Updates the name of an existing product.
     *
     * @param productId the ID of the product to update
     * @param updateNameDTO the data transfer object containing the new name
     * @return ResponseEntity containing the updated ProductDTO
     */
    @PutMapping("/name/{productId}")
    @Operation(summary = "Update the name of a product")
    ResponseEntity<ProductDTO> updateProductName(@PathVariable Long productId, @RequestBody UpdateNameDTO updateNameDTO);

    /**
     * Deletes a product.
     *
     * @param productId the ID of the product to be deleted
     * @return ResponseEntity indicating the result of the deletion
     */
    @DeleteMapping("/{productId}")
    @Operation(summary = "Delete a product")
    ResponseEntity<Void> deleteProduct(@PathVariable Long productId);

    /**
     * Retrieves products with the most stock for a specific franchise.
     *
     * @param franchiseId the ID of the franchise to get products from
     * @return ResponseEntity containing a list of ProductDTOs with the most stock
     */
    @GetMapping("/most-stock/{franchiseId}")
    @Operation(summary = "Get products with the most stock for a specific franchise")
    ResponseEntity<List<ProductDTO>> getProductsWithMostStockByFranchise(@PathVariable Long franchiseId);

}