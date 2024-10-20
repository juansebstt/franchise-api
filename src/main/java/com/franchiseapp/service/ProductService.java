package com.franchiseapp.service;

import com.franchiseapp.commons.dtos.UpdateNameDTO;
import com.franchiseapp.commons.entities.ProductModel;

import java.util.List;

public interface ProductService {

    ProductModel createProduct(Long branchId, ProductModel productModel); // Create a new product

    ProductModel updateProductStock(Long productId, Integer stock); // Update stock for a product

    ProductModel updateProductName(Long productId, UpdateNameDTO updateNameDTO); // Update product name

    void deleteProduct(Long productId); // Delete a product

    List<ProductModel> getProductsWithMostStockByFranchise(Long franchiseId);

}
