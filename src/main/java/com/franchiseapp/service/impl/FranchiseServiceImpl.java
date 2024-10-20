package com.franchiseapp.service.impl;

import com.franchiseapp.commons.dtos.UpdateNameDTO;
import com.franchiseapp.commons.entities.FranchiseModel;
import com.franchiseapp.repositories.FranchiseRepository;
import com.franchiseapp.service.FranchiseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FranchiseServiceImpl implements FranchiseService {

    private FranchiseRepository franchiseRepository;

    @Override
    public FranchiseModel createFranchise(FranchiseModel franchiseModel) {
        return franchiseRepository.save(franchiseModel);
    }

    @Override
    public FranchiseModel updateFranchiseName(Long id, UpdateNameDTO updateNameDTO) {
        FranchiseModel franchise = franchiseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Franchise not found"));
        franchise.setName(updateNameDTO.getNameDTO());
        return franchiseRepository.save(franchise);
    }

    @Override
    public List<FranchiseModel> getAllFranchises() {
        return franchiseRepository.findAll();
    }

}
