package com.franchiseapp.commons.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Entity representing a product in the franchise application.
 * This class maps to the 'products' table in the database.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class ProductModel {

    /**
     * Unique identifier for the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Name of the product. This field is required and cannot be blank.
     */
    @NotNull(message = "Product name is required")
    @NotBlank(message = "Product name cannot be blank")
    private String name;

    /**
     * Stock quantity of the product. This field is required.
     */
    @NotNull(message = "Stock quantity is required")
    private Integer stock;

    /**
     * Unique identifier for the branch associated with this product.
     */
    @Column(name = "branch_id")
    private Long branchId;

    /**
     * The branch associated with this product.
     * This is a many-to-one relationship with the BranchModel.
     */
    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id", insertable = false, updatable = false)
    private BranchModel branch;

}
