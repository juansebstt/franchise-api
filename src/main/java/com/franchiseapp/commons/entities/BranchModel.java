package com.franchiseapp.commons.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "branches")
public class BranchModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Branch name is required")
    @NotBlank(message = "Branch name cannot be blank")
    private String name;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private FranchiseModel franchise;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private List<ProductModel> products;

}
