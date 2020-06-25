package com.dietly.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class DietCalories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dietCaloriesId;
    private Integer calories;

    @ManyToOne
    @JsonBackReference
    private DietOption dietOption;
}
