package com.dietly.model.requests;

import lombok.Data;

@Data
public class AssignDietOptionToDiet {
    private Integer dietOptionId;
    private Integer dietId;
}
