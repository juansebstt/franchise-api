package com.franchiseapp.service;

import com.franchiseapp.commons.dtos.UpdateNameDTO;
import com.franchiseapp.commons.entities.BranchModel;

public interface BranchService {

    BranchModel createBranch(Long franchiseId, BranchModel branchModel);  // Pass franchise ID to link it

    BranchModel updateBranchName(Long id, UpdateNameDTO updateNameDTO);

}
