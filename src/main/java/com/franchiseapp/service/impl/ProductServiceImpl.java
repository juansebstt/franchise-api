package com.franchiseapp.service.impl;

import com.franchiseapp.commons.dtos.ProductDTO;
import com.franchiseapp.commons.dtos.UpdateNameDTO;
import com.franchiseapp.commons.entities.ProductModel;
import com.franchiseapp.repositories.ProductRepository;
import com.franchiseapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the ProductService interface for managing products within branches.
 * This service handles the business logic for creating, updating, deleting, and retrieving products.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Creates a new product associated with a specific branch.
     *
     * @param branchId The ID of the branch where the product belongs.
     * @param product   The ProductDTO containing the details of the product to be created.
     * @return The created ProductDTO, including the generated ID.
     */
    @Override
    public ProductDTO createProduct(Long branchId, ProductDTO product) {

        ProductModel productModel = new ProductModel();
        productModel.setName(product.getName());
        productModel.setBranchId(branchId);
        productModel.setStock(product.getStock());

        var createdProduct = productRepository.save(productModel);

        return ProductDTO.builder()
                .id(createdProduct.getId())
                .name(createdProduct.getName())
                .branchId(createdProduct.getBranchId())
                .stock(createdProduct.getStock())
                .build();
    }

    /**
     * Updates the stock of an existing product identified by its ID.
     *
     * @param productId The ID of the product to be updated.
     * @param stock     The new stock value for the product.
     * @return The updated ProductDTO.
     * @throws RuntimeException If the product is not found.
     */
    @Override
    public ProductDTO updateProductStock(Long productId, Integer stock) {
        // Find the product by ID, update stock, and save
        return productRepository.findById(productId)
                .map(product -> {
                    product.setStock(stock);
                    ProductModel updatedProduct = productRepository.save(product);
                    return ProductDTO.builder()
                            .id(updatedProduct.getId())
                            .name(updatedProduct.getName())
                            .branchId(updatedProduct.getBranchId())
                            .stock(updatedProduct.getStock())
                            .build();
                })
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    /**
     * Updates the name of an existing product identified by its ID.
     *
     * @param productId       The ID of the product to be updated.
     * @param updateNameDTO   The UpdateNameDTO containing the new name for the product.
     * @return The updated ProductDTO.
     * @throws RuntimeException If the product is not found.
     */
    @Override
    public ProductDTO updateProductName(Long productId, UpdateNameDTO updateNameDTO) {

        // Find the product by ID, update name, and save
        return productRepository.findById(productId)
                .map(product -> {
                    product.setName(updateNameDTO.getNameDTO());
                    ProductModel updatedProduct = productRepository.save(product);
                    return ProductDTO.builder()
                            .id(updatedProduct.getId())
                            .name(updatedProduct.getName())
                            .branchId(updatedProduct.getBranchId())
                            .stock(updatedProduct.getStock())
                            .build();
                })
                .orElseThrow(() -> new RuntimeException("Product not found")); // Handle not found scenario
    }

    /**
     * Deletes a product identified by its ID.
     *
     * @param productId The ID of the product to be deleted.
     * @throws IllegalArgumentException If the productId is null.
     * @throws RuntimeException If the product is not found.
     */
    @Override
    public void deleteProduct(Long productId) {
        // Validate null productId
        if (productId == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }

        productRepository.findById(productId).ifPresentOrElse(
                product -> productRepository.deleteById(productId), // Delete if found
                () -> {
                    throw new RuntimeException("Product not found");
                } // Handle not found
        );
    }

    /**
     * Retrieves products with the most stock for each branch within a specific franchise.
     *
     * @param franchiseId The ID of the franchise for which to retrieve products.
     * @return A list of ProductDTOs representing the products with the most stock.
     */
    @Override
    public List<ProductDTO> getProductsWithMostStockByFranchise(Long franchiseId) {

        var products = productRepository.findTopProductByStockForEachBranchInFranchise(franchiseId);

        return products.stream()
                .map(productModel -> {
                    return ProductDTO.builder()
                            .id(productModel.getId())
                            .name(productModel.getName())
                            .branchId(productModel.getBranchId())
                            .stock(productModel.getStock())
                            .build();
                }).toList();
    }
}
