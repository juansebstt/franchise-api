package com.franchiseapp.service.impl;

import com.franchiseapp.commons.dto.BranchDTO;
import com.franchiseapp.commons.dto.FranchiseDTO;
import com.franchiseapp.commons.dto.UpdateNameDTO;
import com.franchiseapp.commons.entity.FranchiseModel;
import com.franchiseapp.repository.BranchRepository;
import com.franchiseapp.repository.FranchiseRepository;
import com.franchiseapp.service.FranchiseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the FranchiseService interface for managing franchises and their branches.
 * This service handles the business logic for creating, updating, and retrieving franchises.
 */
@Service
public class FranchiseServiceImpl implements FranchiseService {

    private final FranchiseRepository franchiseRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public FranchiseServiceImpl(FranchiseRepository franchiseRepository, BranchRepository branchRepository) {
        this.franchiseRepository = franchiseRepository;
        this.branchRepository = branchRepository;
    }

    /**
     * Creates a new franchise.
     *
     * @param franchise The FranchiseDTO containing the details of the franchise to be created.
     * @return The created FranchiseDTO, including the generated ID.
     * @throws RuntimeException If the franchise fails to save.
     */
    @Override
    public FranchiseDTO createFranchise(FranchiseDTO franchise) {

        var franchiseModel = FranchiseModel.builder().name(franchise.getName()).build();
        var created = Optional.of(franchiseModel)
                .map(franchiseRepository::save)
                .orElseThrow(() -> new RuntimeException("Failed to save franchise"));
        return FranchiseDTO.builder().id(created.getId()).name(created.getName()).build();
    }

    /**
     * Updates the name of an existing franchise identified by its ID.
     *
     * @param id              The ID of the franchise to be updated.
     * @param updateNameDTO   The UpdateNameDTO containing the new name for the franchise.
     * @return The updated FranchiseDTO.
     * @throws EntityNotFoundException If no franchise is found with the given ID.
     */
    @Override
    public FranchiseDTO updateFranchiseName(Long id, UpdateNameDTO updateNameDTO) {

        var franchiseModel =  franchiseRepository.findById(id)
                .map(franchise -> {
                    franchise.setName(updateNameDTO.getNameDTO());
                    return franchiseRepository.save(franchise);
                })
                .orElseThrow(() -> new EntityNotFoundException("Franchise not found with id: " + id));
        return FranchiseDTO.builder().id(franchiseModel.getId()).name(franchiseModel.getName()).build();
    }

    /**
     * Retrieves all franchises along with their branches.
     *
     * @return A list of FranchiseDTOs, each including its associated branches.
     */
    @Override
    public List<FranchiseDTO> getAllFranchises() {

        var franchises = franchiseRepository.findAll()
                .stream()
                .filter(franchise -> franchise.getName() != null)
                .toList();

        return franchises.stream()
                .map(franchise -> {
                    var branches = this.branchRepository.findByFranchiseId(franchise.getId());
                    var branchDTOList = new ArrayList<BranchDTO>();
                    branches.forEach(branch -> {
                        var branchDto = new BranchDTO();
                        branchDto.setId(branch.getId());
                        branchDto.setName(branch.getName());
                        branchDto.setFranchiseId(franchise.getId());
                        branchDTOList.add(branchDto);
                    });

                    return FranchiseDTO.builder().id(franchise.getId()).name(franchise.getName())
                            .branches(branchDTOList).build();
                }).toList();
    }
}
