package com.dietly.model.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignDietOptionToDiet {
    private Integer dietOptionId;
    private Integer DietId;

}
