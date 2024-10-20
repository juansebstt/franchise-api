package com.franchiseapp.commons.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchDTO {

    private Long id;
    private String name;
    private Long franchiseId;
    private FranchiseDTO franchise;

}
