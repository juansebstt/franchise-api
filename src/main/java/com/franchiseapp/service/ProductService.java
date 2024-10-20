package com.franchiseapp.service;

import com.franchiseapp.commons.dtos.ProductDTO;
import com.franchiseapp.commons.dtos.UpdateNameDTO;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct(Long branchId, ProductDTO product); // Create a new product

    ProductDTO updateProductStock(Long productId, Integer stock); // Update stock for a product

    ProductDTO updateProductName(Long productId, UpdateNameDTO updateNameDTO); // Update product name

    void deleteProduct(Long productId); // Delete a product

    List<ProductDTO> getProductsWithMostStockByFranchise(Long franchiseId);

}
