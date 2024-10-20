package com.franchiseapp.commons.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FranchiseDTO {

    private Long id;
    private String name;
    private List<BranchDTO> branches;
}
