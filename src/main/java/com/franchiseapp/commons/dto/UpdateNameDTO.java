package com.franchiseapp.commons.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for updating the name of a franchise, branch, or product.
 * This class is used to transfer name update requests between layers of the application.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNameDTO {

    /**
     * The new name to be assigned to a franchise, branch, or product.
     * This field must not be null or blank.
     */
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    @Schema(description = "The new name to be assigned", example = "New Franchise Name")
    private String nameDTO;

}
