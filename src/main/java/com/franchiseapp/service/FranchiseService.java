package com.franchiseapp.service;

import com.franchiseapp.commons.dtos.FranchiseDTO;
import com.franchiseapp.commons.dtos.UpdateNameDTO;

import java.util.List;

/**
 * Service interface for managing franchises.
 * This interface defines the methods for creating, updating, and retrieving franchise information.
 */
public interface FranchiseService {

    /**
     * Creates a new franchise with the provided details.
     *
     * @param franchise The FranchiseDTO containing the details of the franchise to be created.
     * @return The created FranchiseDTO, including the generated ID.
     */
    FranchiseDTO createFranchise(FranchiseDTO franchise);

    /**
     * Updates the name of an existing franchise identified by its ID.
     *
     * @param id              The ID of the franchise to be updated.
     * @param updateNameDTO   The UpdateNameDTO containing the new name for the franchise.
     * @return The updated FranchiseDTO.
     */
    FranchiseDTO updateFranchiseName(Long id, UpdateNameDTO updateNameDTO);

    /**
     * Retrieves a list of all franchises.
     *
     * @return A list of FranchiseDTOs representing all existing franchises.
     */
    List<FranchiseDTO> getAllFranchises();

}
