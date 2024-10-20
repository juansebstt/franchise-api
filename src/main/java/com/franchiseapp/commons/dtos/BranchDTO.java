package com.franchiseapp.commons.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing a branch in the franchise application.
 * This class is used to transfer data between layers of the application.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchDTO {
    /**
     * Unique identifier for the branch.
     */
    @Schema(description = "Unique identifier for the branch", example = "1")
    private Long id;

    /**
     * Name of the branch.
     */
    @NotBlank(message = "Branch name cannot be blank")
    @Schema(description = "Name of the branch", example = "Main Branch")
    private String name;

    /**
     * Unique identifier for the franchise that this branch belongs to.
     */
    @NotNull(message = "Franchise ID cannot be null")
    @Schema(description = "Unique identifier for the franchise associated with this branch", example = "10")
    private Long franchiseId;

    /**
     * The FranchiseDTO representing the franchise associated with this branch.
     */
    @Schema(description = "The franchise associated with this branch")
    private FranchiseDTO franchise;

}
