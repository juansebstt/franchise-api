package com.franchiseapp.commons.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object (DTO) representing a franchise in the franchise application.
 * This class is used to transfer data between layers of the application.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FranchiseDTO {

    /**
     * Unique identifier for the franchise.
     */
    @Schema(description = "Unique identifier for the franchise", example = "1")
    private Long id;

    /**
     * Name of the franchise.
     */
    @NotBlank(message = "Franchise name cannot be blank")
    @Schema(description = "Name of the franchise", example = "Coffee House")
    private String name;

    /**
     * List of branches associated with the franchise.
     */
    @Schema(description = "List of branches associated with the franchise")
    private List<BranchDTO> branches;
}
