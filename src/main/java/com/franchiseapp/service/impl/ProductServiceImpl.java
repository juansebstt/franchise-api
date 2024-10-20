package com.franchiseapp.service.impl;

import com.franchiseapp.commons.dtos.UpdateNameDTO;
import com.franchiseapp.commons.entities.ProductModel;
import com.franchiseapp.repositories.BranchRepository;
import com.franchiseapp.repositories.ProductRepository;
import com.franchiseapp.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

    public ProductServiceImpl(ProductRepository productRepository, BranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public ProductModel createProduct(Long branchId, ProductModel productModel) {

        return branchRepository.findById(branchId)
                .map(branch -> {
                    productModel.setBranch(branch);
                    return productRepository.save(productModel);
                })
                .orElseThrow(() -> new RuntimeException("Branch not found with id: " + branchId));
    }

    @Override
    public ProductModel updateProductStock(Long productId, Integer stock) {

        return productRepository.findById(productId)
                .map(product -> {
                    product.setStock(stock);
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
    }

    @Override
    public ProductModel updateProductName(Long productId, UpdateNameDTO updateNameDTO) {

        return productRepository.findById(productId)
                .map(product -> {
                    product.setName(updateNameDTO.getNameDTO());
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
    }

    @Override
    public void deleteProduct(Long productId) {

        productRepository.deleteById(productId);
    }
}
