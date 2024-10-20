package com.franchiseapp.controller.impl;

import com.franchiseapp.commons.dtos.ProductDTO;
import com.franchiseapp.commons.dtos.UpdateNameDTO;
import com.franchiseapp.controller.ProductApi;
import com.franchiseapp.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController implements ProductApi {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ResponseEntity<ProductDTO> createProduct(Long branchId, ProductDTO product) {
        return ResponseEntity.ok(productService.createProduct(branchId, product));
    }

    @Override
    public ResponseEntity<ProductDTO> updateProductStock(Long productId, Integer stock) {
        return ResponseEntity.ok(productService.updateProductStock(productId, stock));
    }

    @Override
    public ResponseEntity<ProductDTO> updateProductName(Long productId, UpdateNameDTO updateNameDTO) {
        return ResponseEntity.ok(productService.updateProductName(productId, updateNameDTO));
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getProductsWithMostStockByFranchise(Long franchiseId) {
        return ResponseEntity.ok(productService.getProductsWithMostStockByFranchise(franchiseId));
    }

}
