package com.franchiseapp.controller.impl;

import com.franchiseapp.commons.dtos.ProductDTO;
import com.franchiseapp.commons.dtos.UpdateNameDTO;
import com.franchiseapp.controller.ProductApi;
import com.franchiseapp.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for managing product operations in the franchise application.
 * This class implements the ProductApi interface to define the endpoints.
 */
@RestController
public class ProductController implements ProductApi {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Creates a new product under a specific branch.
     *
     * @param branchId the ID of the branch where the product will be created
     * @param product the product data transfer object containing product details
     * @return ResponseEntity containing the created ProductDTO
     */
    @Override
    public ResponseEntity<ProductDTO> createProduct(Long branchId, ProductDTO product) {
        return ResponseEntity.ok(productService.createProduct(branchId, product));
    }

    /**
     * Updates the stock quantity of an existing product.
     *
     * @param productId the ID of the product to be updated
     * @param stock the new stock quantity
     * @return ResponseEntity containing the updated ProductDTO
     */
    @Override
    public ResponseEntity<ProductDTO> updateProductStock(Long productId, Integer stock) {
        return ResponseEntity.ok(productService.updateProductStock(productId, stock));
    }

    /**
     * Updates the name of an existing product.
     *
     * @param productId the ID of the product to be updated
     * @param updateNameDTO the data transfer object containing the new name
     * @return ResponseEntity containing the updated ProductDTO
     */
    @Override
    public ResponseEntity<ProductDTO> updateProductName(Long productId, UpdateNameDTO updateNameDTO) {
        return ResponseEntity.ok(productService.updateProductName(productId, updateNameDTO));
    }

    /**
     * Deletes a product by its ID.
     *
     * @param productId the ID of the product to be deleted
     * @return ResponseEntity with no content
     */
    @Override
    public ResponseEntity<Void> deleteProduct(Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves products with the most stock for a specific franchise.
     *
     * @param franchiseId the ID of the franchise to get products from
     * @return ResponseEntity containing a list of ProductDTOs with the most stock
     */
    @Override
    public ResponseEntity<List<ProductDTO>> getProductsWithMostStockByFranchise(Long franchiseId) {
        return ResponseEntity.ok(productService.getProductsWithMostStockByFranchise(franchiseId));
    }

}
