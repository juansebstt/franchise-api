package com.franchiseapp.service.impl;

import com.franchiseapp.commons.dtos.ProductDTO;
import com.franchiseapp.commons.dtos.UpdateNameDTO;
import com.franchiseapp.commons.entities.ProductModel;
import com.franchiseapp.repositories.BranchRepository;
import com.franchiseapp.repositories.ProductRepository;
import com.franchiseapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, BranchRepository branchRepository) {
        this.productRepository = productRepository;
    }

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

    @Override
    public void deleteProduct(Long productId) {
        // Validate null productId
        if (productId == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }

        productRepository.findById(productId).ifPresentOrElse(
                product -> productRepository.deleteById(productId), // Delete if found
                () -> { throw new RuntimeException("Product not found"); } // Handle not found
        );
    }

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
