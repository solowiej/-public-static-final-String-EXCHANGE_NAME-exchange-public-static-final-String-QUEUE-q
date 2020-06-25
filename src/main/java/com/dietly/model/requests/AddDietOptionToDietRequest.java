package com.dietly.model.requests;

import lombok.Data;

@Data
public class AddDietOptionToDietRequest {
    private Integer dietId;
    private String name;
    private String abbreviation;
}
