package com.franchiseapp.service;

import com.franchiseapp.commons.dtos.BranchDTO;
import com.franchiseapp.commons.dtos.UpdateNameDTO;

public interface BranchService {

    BranchDTO createBranch(Long franchiseId, BranchDTO branch);  // Pass franchise ID to link it

    BranchDTO updateBranchName(Long id, UpdateNameDTO updateNameDTO);

}
