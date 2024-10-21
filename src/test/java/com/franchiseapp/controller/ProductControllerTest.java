package com.franchiseapp.controller;

import com.franchiseapp.commons.dto.ProductDTO;
import com.franchiseapp.commons.dto.UpdateNameDTO;
import com.franchiseapp.controller.impl.ProductController;
import com.franchiseapp.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProduct() {
        // Arrange
        Long branchId = 1L;
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Test Product");
        productDTO.setBranchId(branchId);
        productDTO.setStock(100);

        when(productService.createProduct(any(Long.class), any(ProductDTO.class))).thenReturn(productDTO);

        // Act
        ResponseEntity<ProductDTO> response = productController.createProduct(branchId, productDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDTO, response.getBody());
    }

    @Test
    public void testUpdateProductStock() {
        // Arrange
        Long productId = 1L;
        Integer newStock = 150;
        ProductDTO updatedProductDTO = new ProductDTO();
        updatedProductDTO.setId(productId);
        updatedProductDTO.setName("Test Product");
        updatedProductDTO.setBranchId(1L);
        updatedProductDTO.setStock(newStock);

        when(productService.updateProductStock(productId, newStock)).thenReturn(updatedProductDTO);

        // Act
        ResponseEntity<ProductDTO> response = productController.updateProductStock(productId, newStock);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProductDTO, response.getBody());
    }

    @Test
    public void testUpdateProductName() {
        // Arrange
        Long productId = 1L;
        String newName = "Updated Product Name";
        ProductDTO updatedProductDTO = new ProductDTO();
        updatedProductDTO.setId(productId);
        updatedProductDTO.setName(newName);
        updatedProductDTO.setBranchId(1L);
        updatedProductDTO.setStock(100);

        UpdateNameDTO updateNameDTO = new UpdateNameDTO();
        updateNameDTO.setNameDTO(newName);

        when(productService.updateProductName(productId, updateNameDTO)).thenReturn(updatedProductDTO);

        // Act
        ResponseEntity<ProductDTO> response = productController.updateProductName(productId, updateNameDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProductDTO, response.getBody());
    }

    @Test
    public void testDeleteProduct() {
        // Arrange
        Long productId = 1L;

        // Act
        ResponseEntity<Void> response = productController.deleteProduct(productId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetProductsWithMostStockByFranchise() {
        // Arrange
        Long franchiseId = 1L;
        ProductDTO product1 = new ProductDTO();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setBranchId(1L);
        product1.setStock(200);

        ProductDTO product2 = new ProductDTO();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setBranchId(2L);
        product2.setStock(300);

        List<ProductDTO> products = Arrays.asList(product1, product2);

        when(productService.getProductsWithMostStockByFranchise(franchiseId)).thenReturn(products);

        // Act
        ResponseEntity<List<ProductDTO>> response = productController.getProductsWithMostStockByFranchise(franchiseId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }
}