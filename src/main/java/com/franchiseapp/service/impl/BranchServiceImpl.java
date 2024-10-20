package com.franchiseapp.service.impl;

import com.franchiseapp.commons.dtos.BranchDTO;
import com.franchiseapp.commons.dtos.UpdateNameDTO;
import com.franchiseapp.commons.entities.BranchModel;
import com.franchiseapp.repositories.BranchRepository;
import com.franchiseapp.repositories.FranchiseRepository;
import com.franchiseapp.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the BranchService interface for managing branches within franchises.
 * This service handles the business logic for creating and updating branches.
 */
@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, FranchiseRepository franchiseRepository) {
        this.branchRepository = branchRepository;
    }

    /**
     * Creates a new branch for a specified franchise.
     *
     * @param franchiseId The ID of the franchise for which the branch is created.
     * @param branch      The BranchDTO containing the details of the branch to be created.
     * @return The created BranchDTO, including the generated ID.
     */
    @Override
    public BranchDTO createBranch(Long franchiseId, BranchDTO branch) {

        BranchModel branchModel = new BranchModel();
        branchModel.setName(branch.getName());
        branchModel.setFranchiseId(franchiseId);

        var createdBranch = this.branchRepository.save(branchModel);

        return BranchDTO.builder()
                .id(createdBranch.getId())
                .name(createdBranch.getName())
                .franchiseId(createdBranch.getFranchiseId())
                .build();
    }

    /**
     * Updates the name of an existing branch identified by its ID.
     *
     * @param id              The ID of the branch to be updated.
     * @param updateNameDTO   The UpdateNameDTO containing the new name for the branch.
     * @return The updated BranchDTO.
     * @throws RuntimeException If no branch is found with the given ID.
     */
    @Override
    public BranchDTO updateBranchName(Long id, UpdateNameDTO updateNameDTO) {

        var updated = branchRepository.findById(id)
                .map(branch -> {
                    branch.setName(updateNameDTO.getNameDTO());
                    return branchRepository.save(branch);
                })
                .orElseThrow(() -> new RuntimeException("Branch not found with id: " + id));

        return BranchDTO.builder().id(updated.getId()).name(updated.getName()).build();
    }
}
