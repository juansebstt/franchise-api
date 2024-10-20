package com.franchiseapp.commons.entities;

import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Entity representing a branch in the franchise application.
 * This class maps to the 'branches' table in the database.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "branches")
public class BranchModel {

    /**
     * Unique identifier for the branch.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the branch. This field is required and cannot be blank.
     */
    @NotNull(message = "Branch name is required")
    @NotBlank(message = "Branch name cannot be blank")
    private String name;

    /**
     * Unique identifier for the franchise associated with this branch.
     */
    @Column(name = "franchise_id")
    private Long franchiseId;

    /**
     * The franchise associated with this branch.
     * This is a many-to-one relationship with the FranchiseModel.
     */
    @ManyToOne
    @JoinColumn(name = "franchise_id", referencedColumnName = "id", insertable = false, updatable = false)
    private FranchiseModel franchise;

    /**
     * List of products available at this branch.
     * This is a one-to-many relationship with the ProductModel.
     */
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductModel> products;

}
