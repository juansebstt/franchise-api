package com.franchiseapp.service;

import com.franchiseapp.commons.dtos.ProductDTO;
import com.franchiseapp.commons.dtos.UpdateNameDTO;

import java.util.List;

/**
 * Service interface for managing products within branches.
 * This interface defines methods for creating, updating, deleting, and retrieving product information.
 */
public interface ProductService {

    /**
     * Creates a new product associated with a specific branch.
     *
     * @param branchId The ID of the branch where the product will be created.
     * @param product  The ProductDTO containing the details of the product to be created.
     * @return The created ProductDTO, including the generated ID and other details.
     */
    ProductDTO createProduct(Long branchId, ProductDTO product); // Create a new product

    /**
     * Updates the stock level for an existing product identified by its ID.
     *
     * @param productId The ID of the product to be updated.
     * @param stock     The new stock level for the product.
     * @return The updated ProductDTO reflecting the new stock level.
     */
    ProductDTO updateProductStock(Long productId, Integer stock); // Update stock for a product

    /**
     * Updates the name of an existing product identified by its ID.
     *
     * @param productId      The ID of the product to be updated.
     * @param updateNameDTO  The UpdateNameDTO containing the new name for the product.
     * @return The updated ProductDTO reflecting the new name.
     */
    ProductDTO updateProductName(Long productId, UpdateNameDTO updateNameDTO); // Update product name

    /**
     * Deletes an existing product identified by its ID.
     *
     * @param productId The ID of the product to be deleted.
     */
    void deleteProduct(Long productId); // Delete a product

    /**
     * Retrieves a list of products with the most stock for a specific franchise.
     *
     * @param franchiseId The ID of the franchise for which to retrieve products.
     * @return A list of ProductDTOs representing the products with the highest stock levels within the franchise.
     */
    List<ProductDTO> getProductsWithMostStockByFranchise(Long franchiseId);

}
