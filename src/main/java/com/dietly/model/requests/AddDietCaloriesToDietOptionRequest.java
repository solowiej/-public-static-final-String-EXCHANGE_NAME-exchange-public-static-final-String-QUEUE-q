package com.dietly.model.requests;

import lombok.Data;

@Data
public class AddDietCaloriesToDietOptionRequest {
    private Integer dietOptionId;
    private Integer calories;
}
