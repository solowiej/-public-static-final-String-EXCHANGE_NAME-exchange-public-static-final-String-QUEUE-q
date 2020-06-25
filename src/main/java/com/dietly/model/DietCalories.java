package com.dietly.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Data
public class DietCalories {
    private Integer dietCaloriesId;
    private Integer calories;

    @JsonBackReference
    private DietOption dietOption;
}
