package com.franchiseapp.service;

import com.franchiseapp.commons.dto.BranchDTO;
import com.franchiseapp.commons.dto.FranchiseDTO;
import com.franchiseapp.commons.dto.UpdateNameDTO;
import com.franchiseapp.commons.entity.FranchiseModel;
import com.franchiseapp.repository.BranchRepository;
import com.franchiseapp.repository.FranchiseRepository;
import com.franchiseapp.service.impl.FranchiseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FranchiseServiceImplTest {

    @InjectMocks
    private FranchiseServiceImpl franchiseService;

    @Mock
    private FranchiseRepository franchiseRepository;

    @Mock
    private BranchRepository branchRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateFranchise() {
        FranchiseDTO franchiseDTO = FranchiseDTO.builder().name("Franchise A").build();
        FranchiseModel franchiseModel = FranchiseModel.builder().id(1L).name("Franchise A").build();

        when(franchiseRepository.save(any(FranchiseModel.class))).thenReturn(franchiseModel);

        FranchiseDTO createdFranchise = franchiseService.createFranchise(franchiseDTO);

        assertNotNull(createdFranchise);
        assertEquals(1L, createdFranchise.getId());
        assertEquals("Franchise A", createdFranchise.getName());
        verify(franchiseRepository, times(1)).save(any(FranchiseModel.class));
    }

    @Test
    public void testUpdateFranchiseName_Success() {
        Long franchiseId = 1L;
        UpdateNameDTO updateNameDTO = UpdateNameDTO.builder().nameDTO("Updated Franchise").build();
        FranchiseModel existingFranchise = FranchiseModel.builder().id(franchiseId).name("Franchise A").build();

        when(franchiseRepository.findById(franchiseId)).thenReturn(Optional.of(existingFranchise));
        when(franchiseRepository.save(existingFranchise)).thenReturn(existingFranchise);

        FranchiseDTO updatedFranchise = franchiseService.updateFranchiseName(franchiseId, updateNameDTO);

        assertNotNull(updatedFranchise);
        assertEquals(franchiseId, updatedFranchise.getId());
        assertEquals("Updated Franchise", updatedFranchise.getName());
        verify(franchiseRepository, times(1)).findById(franchiseId);
        verify(franchiseRepository, times(1)).save(existingFranchise);
    }

    @Test
    public void testUpdateFranchiseName_NotFound() {
        Long franchiseId = 1L;
        UpdateNameDTO updateNameDTO = UpdateNameDTO.builder().nameDTO("Updated Franchise").build();

        when(franchiseRepository.findById(franchiseId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> franchiseService.updateFranchiseName(franchiseId, updateNameDTO));
        verify(franchiseRepository, times(1)).findById(franchiseId);
        verify(franchiseRepository, times(0)).save(any(FranchiseModel.class)); // Ensure save is not called
    }

    @Test
    public void testGetAllFranchises() {
        FranchiseModel franchise1 = FranchiseModel.builder().id(1L).name("Franchise A").build();
        FranchiseModel franchise2 = FranchiseModel.builder().id(2L).name("Franchise B").build();

        List<FranchiseModel> franchises = List.of(franchise1, franchise2);
        when(franchiseRepository.findAll()).thenReturn(franchises);
        when(branchRepository.findByFranchiseId(1L)).thenReturn(new ArrayList<>());
        when(branchRepository.findByFranchiseId(2L)).thenReturn(new ArrayList<>());

        List<FranchiseDTO> franchiseDTOs = franchiseService.getAllFranchises();

        assertNotNull(franchiseDTOs);
        assertEquals(2, franchiseDTOs.size());
        assertEquals("Franchise A", franchiseDTOs.get(0).getName());
        assertEquals("Franchise B", franchiseDTOs.get(1).getName());
        verify(franchiseRepository, times(1)).findAll();
    }
}
