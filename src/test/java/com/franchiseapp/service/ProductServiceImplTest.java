package com.franchiseapp.service;

import com.franchiseapp.commons.dto.ProductDTO;
import com.franchiseapp.commons.dto.UpdateNameDTO;
import com.franchiseapp.commons.entity.ProductModel;
import com.franchiseapp.repository.ProductRepository;
import com.franchiseapp.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProduct() {
        Long branchId = 1L;
        ProductDTO productDTO = ProductDTO.builder().name("Product A").stock(100).build();
        ProductModel productModel = new ProductModel();
        productModel.setId(1L);
        productModel.setName("Product A");
        productModel.setBranchId(branchId);
        productModel.setStock(100);

        when(productRepository.save(any(ProductModel.class))).thenReturn(productModel);

        ProductDTO createdProduct = productService.createProduct(branchId, productDTO);

        assertNotNull(createdProduct);
        assertEquals(1L, createdProduct.getId());
        assertEquals("Product A", createdProduct.getName());
        assertEquals(branchId, createdProduct.getBranchId());
        assertEquals(100, createdProduct.getStock());
        verify(productRepository, times(1)).save(any(ProductModel.class));
    }

    @Test
    public void testUpdateProductStock_Success() {
        Long productId = 1L;
        Integer newStock = 150;
        ProductModel existingProduct = new ProductModel();
        existingProduct.setId(productId);
        existingProduct.setStock(100);

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(existingProduct);

        ProductDTO updatedProduct = productService.updateProductStock(productId, newStock);

        assertNotNull(updatedProduct);
        assertEquals(productId, updatedProduct.getId());
        assertEquals(newStock, updatedProduct.getStock());
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(existingProduct);
    }

    @Test
    public void testUpdateProductStock_NotFound() {
        Long productId = 1L;
        Integer newStock = 150;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.updateProductStock(productId, newStock));
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(0)).save(any(ProductModel.class));
    }

    @Test
    public void testUpdateProductName_Success() {
        Long productId = 1L;
        UpdateNameDTO updateNameDTO = UpdateNameDTO.builder().nameDTO("Updated Product").build();
        ProductModel existingProduct = new ProductModel();
        existingProduct.setId(productId);
        existingProduct.setName("Product A");

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(existingProduct);

        ProductDTO updatedProduct = productService.updateProductName(productId, updateNameDTO);

        assertNotNull(updatedProduct);
        assertEquals(productId, updatedProduct.getId());
        assertEquals("Updated Product", updatedProduct.getName());
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(existingProduct);
    }

    @Test
    public void testUpdateProductName_NotFound() {
        Long productId = 1L;
        UpdateNameDTO updateNameDTO = UpdateNameDTO.builder().nameDTO("Updated Product").build();

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.updateProductName(productId, updateNameDTO));
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(0)).save(any(ProductModel.class));
    }

    @Test
    public void testDeleteProduct_Success() {
        Long productId = 1L;
        ProductModel existingProduct = new ProductModel();
        existingProduct.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    public void testDeleteProduct_NotFound() {
        Long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.deleteProduct(productId));
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(0)).deleteById(productId);
    }

    @Test
    public void testGetProductsWithMostStockByFranchise() {
        Long franchiseId = 1L;
        ProductModel productModel1 = new ProductModel();
        productModel1.setId(1L);
        productModel1.setName("Product A");
        productModel1.setBranchId(1L);
        productModel1.setStock(150);

        ProductModel productModel2 = new ProductModel();
        productModel2.setId(2L);
        productModel2.setName("Product B");
        productModel2.setBranchId(2L);
        productModel2.setStock(200);

        when(productRepository.findTopProductByStockForEachBranchInFranchise(franchiseId))
                .thenReturn(List.of(productModel1, productModel2));

        List<ProductDTO> productsWithMostStock = productService.getProductsWithMostStockByFranchise(franchiseId);

        assertNotNull(productsWithMostStock);
        assertEquals(2, productsWithMostStock.size());
        assertEquals("Product A", productsWithMostStock.get(0).getName());
        assertEquals("Product B", productsWithMostStock.get(1).getName());
        verify(productRepository, times(1)).findTopProductByStockForEachBranchInFranchise(franchiseId);
    }
}