package com.franchiseapp.service;

import com.franchiseapp.commons.dtos.UpdateNameDTO;
import com.franchiseapp.commons.entities.FranchiseModel;

import java.util.List;

public interface FranchiseService {

    FranchiseModel createFranchise(FranchiseModel franchiseModel);

    FranchiseModel updateFranchiseName(Long id, UpdateNameDTO updateNameDTO);

    List<FranchiseModel> getAllFranchises();

}
