package com.franchiseapp.commons.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing a product in the franchise application.
 * This class is used to transfer data between layers of the application.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    /**
     * Unique identifier for the product.
     */
    @Schema(description = "Unique identifier for the product", example = "1")
    private Long id;

    /**
     * Name of the product.
     */
    @NotBlank(message = "Product name cannot be blank")
    @Schema(description = "Name of the product", example = "Espresso")
    private String name;

    /**
     * Unique identifier for the branch that this product belongs to.
     */
    @NotNull(message = "Branch ID cannot be null")
    @Schema(description = "Unique identifier for the branch associated with this product", example = "10")
    private Long branchId;

    /**
     * Current stock level of the product.
     */
    @NotNull(message = "Stock cannot be null")
    @Schema(description = "Current stock level of the product", example = "50")
    private Integer stock;

    /**
     * The BranchDTO representing the branch associated with this product.
     * This field is ignored during JSON serialization.
     */
    @JsonIgnore
    @Schema(description = "The branch associated with the product")
    private BranchDTO branch;

}
