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
@Table(name = "franchises")
public class FranchiseModel {

    // Franchise model
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Franchise name is required")
    @NotBlank(message = "Franchise name cannot be blank")
    private String name;

    @OneToMany(mappedBy = "franchise", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BranchModel> branches;

}
