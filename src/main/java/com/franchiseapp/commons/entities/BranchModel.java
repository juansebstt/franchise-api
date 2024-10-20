package com.franchiseapp.commons.entities;

import java.util.List;
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

    @Column(name = "franchise_id")
    private Long franchiseId;

    @ManyToOne
    @JoinColumn(name = "franchise_id", referencedColumnName = "id", insertable = false, updatable = false)
    private FranchiseModel franchise;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductModel> products;

}
