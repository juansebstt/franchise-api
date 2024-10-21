package com.franchiseapp.service;

import com.franchiseapp.commons.dto.BranchDTO;
import com.franchiseapp.commons.dto.UpdateNameDTO;
import com.franchiseapp.commons.entity.BranchModel;
import com.franchiseapp.repository.BranchRepository;
import com.franchiseapp.service.impl.BranchServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BranchServiceImplTest {

    @InjectMocks
    private BranchServiceImpl branchService;

    @Mock
    private BranchRepository branchRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBranch() {
        Long franchiseId = 1L;
        BranchDTO branchDTO = BranchDTO.builder().name("Branch A").build();
        BranchModel branchModel = new BranchModel();
        branchModel.setId(1L);
        branchModel.setName("Branch A");
        branchModel.setFranchiseId(franchiseId);

        when(branchRepository.save(any(BranchModel.class))).thenReturn(branchModel);

        BranchDTO createdBranch = branchService.createBranch(franchiseId, branchDTO);

        assertNotNull(createdBranch);
        assertEquals(1L, createdBranch.getId());
        assertEquals("Branch A", createdBranch.getName());
        assertEquals(franchiseId, createdBranch.getFranchiseId());
        verify(branchRepository, times(1)).save(any(BranchModel.class));
    }

    @Test
    public void testUpdateBranchName_Success() {
        Long branchId = 1L;
        UpdateNameDTO updateNameDTO = UpdateNameDTO.builder().nameDTO("Updated Branch").build();
        BranchModel existingBranch = new BranchModel();
        existingBranch.setId(branchId);
        existingBranch.setName("Branch A");

        when(branchRepository.findById(branchId)).thenReturn(Optional.of(existingBranch));
        when(branchRepository.save(existingBranch)).thenReturn(existingBranch);

        BranchDTO updatedBranch = branchService.updateBranchName(branchId, updateNameDTO);

        assertNotNull(updatedBranch);
        assertEquals(branchId, updatedBranch.getId());
        assertEquals("Updated Branch", updatedBranch.getName());
        verify(branchRepository, times(1)).findById(branchId);
        verify(branchRepository, times(1)).save(existingBranch);
    }

    @Test
    public void testUpdateBranchName_NotFound() {
        Long branchId = 1L;
        UpdateNameDTO updateNameDTO = UpdateNameDTO.builder().nameDTO("Updated Branch").build();

        when(branchRepository.findById(branchId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> branchService.updateBranchName(branchId, updateNameDTO));
        verify(branchRepository, times(1)).findById(branchId);
        verify(branchRepository, times(0)).save(any(BranchModel.class)); // Ensure save is not called
    }
}