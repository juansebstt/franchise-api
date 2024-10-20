package com.franchiseapp.service.impl;

import com.franchiseapp.commons.dtos.BranchDTO;
import com.franchiseapp.commons.dtos.UpdateNameDTO;
import com.franchiseapp.commons.entities.BranchModel;
import com.franchiseapp.repositories.BranchRepository;
import com.franchiseapp.repositories.FranchiseRepository;
import com.franchiseapp.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, FranchiseRepository franchiseRepository) {
        this.branchRepository = branchRepository;
    }


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
