package com.dietly.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.Set;

@Data
public class Diet {
    private Integer dietId;
    private String name;
    private String description;

    @JsonManagedReference
    private Set<DietOption> dietOptions;
}
