package com.franchiseapp.controller;

import com.franchiseapp.commons.dto.BranchDTO;
import com.franchiseapp.commons.dto.UpdateNameDTO;
import com.franchiseapp.controller.impl.BranchController;
import com.franchiseapp.service.BranchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BranchControllerTest {

    @InjectMocks
    private BranchController branchController;

    @Mock
    private BranchService branchService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBranch() {
        // Arrange
        Long franchiseId = 1L;
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setId(1L);
        branchDTO.setName("Test Branch");

        when(branchService.createBranch(any(Long.class), any(BranchDTO.class))).thenReturn(branchDTO);

        // Act
        ResponseEntity<BranchDTO> response = branchController.createBranch(franchiseId, branchDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(branchDTO, response.getBody());
    }

    @Test
    public void testUpdateBranchName() {
        // Arrange
        Long branchId = 1L;
        String newName = "Updated Branch Name";
        BranchDTO updatedBranchDTO = new BranchDTO();
        updatedBranchDTO.setId(branchId);
        updatedBranchDTO.setName(newName);

        UpdateNameDTO updateNameDTO = new UpdateNameDTO();
        updateNameDTO.setNameDTO(newName);

        when(branchService.updateBranchName(branchId, updateNameDTO)).thenReturn(updatedBranchDTO);

        // Act
        ResponseEntity<BranchDTO> response = branchController.updateBranch(branchId, updateNameDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBranchDTO, response.getBody());
    }
}
