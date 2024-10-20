package com.franchiseapp.service.impl;

import com.franchiseapp.commons.dtos.BranchDTO;
import com.franchiseapp.commons.dtos.FranchiseDTO;
import com.franchiseapp.commons.dtos.UpdateNameDTO;
import com.franchiseapp.commons.entities.FranchiseModel;
import com.franchiseapp.repositories.BranchRepository;
import com.franchiseapp.repositories.FranchiseRepository;
import com.franchiseapp.service.FranchiseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FranchiseServiceImpl implements FranchiseService {

    private final FranchiseRepository franchiseRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public FranchiseServiceImpl(FranchiseRepository franchiseRepository, BranchRepository branchRepository) {
        this.franchiseRepository = franchiseRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public FranchiseDTO createFranchise(FranchiseDTO franchise) {

        var franchiseModel = FranchiseModel.builder().name(franchise.getName()).build();
        var created = Optional.of(franchiseModel)
                .map(franchiseRepository::save)
                .orElseThrow(() -> new RuntimeException("Failed to save franchise"));
        return FranchiseDTO.builder().id(created.getId()).name(created.getName()).build();
    }

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
