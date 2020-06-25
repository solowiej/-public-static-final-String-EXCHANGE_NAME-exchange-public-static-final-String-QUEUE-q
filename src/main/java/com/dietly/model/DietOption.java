package com.dietly.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.Set;

@Data
public class DietOption {
    private Integer dietOptionId;
    private String name;
    private String abbreviation;

    @JsonBackReference
    private Diet diet;

    @JsonManagedReference
    private Set<DietCalories> dietCalories;
}
