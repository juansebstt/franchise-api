package com.franchiseapp.service.impl;

import com.franchiseapp.commons.dtos.UpdateNameDTO;
import com.franchiseapp.commons.entities.FranchiseModel;
import com.franchiseapp.repositories.FranchiseRepository;
import com.franchiseapp.service.FranchiseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FranchiseServiceImpl implements FranchiseService {

    private FranchiseRepository franchiseRepository;

    @Override
    public FranchiseModel createFranchise(FranchiseModel franchise) {

        return Optional.of(franchise)
                .map(franchiseRepository::save)
                .orElseThrow(() -> new RuntimeException("Failed to save franchise"));
    }

    @Override
    public FranchiseModel updateFranchiseName(Long id, UpdateNameDTO updateNameDTO) {

        return franchiseRepository.findById(id)
                .map(franchise -> {
                    franchise.setName(updateNameDTO.getNameDTO());
                    return franchiseRepository.save(franchise);
                })
                .orElseThrow(() -> new EntityNotFoundException("Franchise not found with id: " + id));
    }

    @Override
    public List<FranchiseModel> getAllFranchises() {

        return franchiseRepository.findAll()
                .stream()
                .filter(franchise -> franchise.getName() != null)
                .collect(Collectors.toList());

    }
}
