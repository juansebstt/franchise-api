package com.franchiseapp.controller;


import com.franchiseapp.commons.dto.FranchiseDTO;
import com.franchiseapp.commons.dto.UpdateNameDTO;
import com.franchiseapp.controller.impl.FranchiseController;
import com.franchiseapp.service.FranchiseService;
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

public class FranchiseControllerTest {

    @InjectMocks
    private FranchiseController franchiseController;

    @Mock
    private FranchiseService franchiseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateFranchise() {
        // Arrange
        FranchiseDTO franchiseDTO = new FranchiseDTO();
        franchiseDTO.setId(1L);
        franchiseDTO.setName("Test Franchise");

        when(franchiseService.createFranchise(any(FranchiseDTO.class))).thenReturn(franchiseDTO);

        // Act
        ResponseEntity<FranchiseDTO> response = franchiseController.createFranchise(franchiseDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(franchiseDTO, response.getBody());
    }

    @Test
    public void testUpdateFranchise() {
        // Arrange
        Long franchiseId = 1L;
        UpdateNameDTO updateNameDTO = new UpdateNameDTO();
        updateNameDTO.setNameDTO("Updated Franchise Name");

        FranchiseDTO updatedFranchiseDTO = new FranchiseDTO();
        updatedFranchiseDTO.setId(franchiseId);
        updatedFranchiseDTO.setName("Updated Franchise Name");

        when(franchiseService.updateFranchiseName(franchiseId, updateNameDTO)).thenReturn(updatedFranchiseDTO);

        // Act
        ResponseEntity<FranchiseDTO> response = franchiseController.updateFranchise(franchiseId, updateNameDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedFranchiseDTO, response.getBody());
    }

    @Test
    public void testGetAllFranchises() {
        // Arrange
        FranchiseDTO franchise1 = new FranchiseDTO();
        franchise1.setId(1L);
        franchise1.setName("Franchise 1");

        FranchiseDTO franchise2 = new FranchiseDTO();
        franchise2.setId(2L);
        franchise2.setName("Franchise 2");

        List<FranchiseDTO> franchises = Arrays.asList(franchise1, franchise2);

        when(franchiseService.getAllFranchises()).thenReturn(franchises);

        // Act
        ResponseEntity<List<FranchiseDTO>> response = franchiseController.getAllFranchises();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(franchises, response.getBody());
    }
}