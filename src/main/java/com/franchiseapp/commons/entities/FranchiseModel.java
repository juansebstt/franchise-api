package com.franchiseapp.commons.entities;

import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Entity representing a franchise in the franchise application.
 * This class maps to the 'franchises' table in the database.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "franchises")
public class FranchiseModel {

    /**
     * Unique identifier for the franchise.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Name of the franchise. This field is required and cannot be blank.
     */
    @NotNull(message = "Franchise name is required")
    @NotBlank(message = "Franchise name cannot be blank")
    private String name;

    /**
     * List of branches associated with this franchise.
     * This is a one-to-many relationship with the BranchModel.
     */
    @OneToMany(mappedBy = "franchise", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BranchModel> branches;

}
