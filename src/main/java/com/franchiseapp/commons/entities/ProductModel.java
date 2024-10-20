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
@Table(name = "products")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Product name is required")
    @NotBlank(message = "Product name cannot be blank")
    private String name;

    @NotNull(message = "Stock quantity is required")
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private BranchModel branch;

}
