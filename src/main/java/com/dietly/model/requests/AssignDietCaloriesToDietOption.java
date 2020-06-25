package com.dietly.model.requests;

import lombok.Data;

@Data
public class AssignDietCaloriesToDietOption {
    private Integer dietOptionId;
    private Integer dietCaloriesId;
}
