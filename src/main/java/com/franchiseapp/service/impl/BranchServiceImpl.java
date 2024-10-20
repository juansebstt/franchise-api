package com.franchiseapp.service.impl;

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
    private final FranchiseRepository franchiseRepository;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, FranchiseRepository franchiseRepository) {
        this.branchRepository = branchRepository;
        this.franchiseRepository = franchiseRepository;
    }


    @Override
    public BranchModel createBranch(Long franchiseId, BranchModel branchModel) {
        branchModel.setFranchise_id(franchiseId);
        return franchiseRepository.findById(franchiseId)
                .map(franchise -> {
                    branchModel.setFranchise(franchise);
                    return branchRepository.save(branchModel);
                })
                .orElseThrow(() -> new RuntimeException("Franchise not found with id: " + franchiseId));
    }

    @Override
    public BranchModel updateBranchName(Long id, UpdateNameDTO updateNameDTO) {
        return branchRepository.findById(id)
                .map(branch -> {
                    branch.setName(updateNameDTO.getNameDTO());
                    return branchRepository.save(branch);
                })
                .orElseThrow(() -> new RuntimeException("Branch not found with id: " + id));
    }
}
