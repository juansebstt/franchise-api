package com.franchiseapp.service;

import com.franchiseapp.commons.dtos.FranchiseDTO;
import com.franchiseapp.commons.dtos.UpdateNameDTO;
import com.franchiseapp.commons.entities.FranchiseModel;

import java.util.List;

public interface FranchiseService {

    FranchiseDTO createFranchise(FranchiseDTO franchise);

    FranchiseDTO updateFranchiseName(Long id, UpdateNameDTO updateNameDTO);

    List<FranchiseModel> getAllFranchises();

}
