package com.dietly.controller;


import com.dietly.model.DietCalories;
import com.dietly.service.DietCaloriesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(DietCaloriesController.API_DIET_CALORIES)
@AllArgsConstructor
public class DietCaloriesController {
    static final String API_DIET_CALORIES = "/api/dietCalories";

    @Autowired
    private DietCaloriesService dietCaloriesService;


    @GetMapping()
    public List<DietCalories> getDietCaloriesList() {
        return dietCaloriesService.getAll();
    }

    @GetMapping("/{id}")
    public DietCalories getById(@PathVariable("id") Integer dietCaloriesId) {
        return dietCaloriesService.getById(dietCaloriesId);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void postDietCalories(@RequestBody DietCalories dietCalories) {
        dietCaloriesService.save(dietCalories);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void putDietCalories(@RequestBody DietCalories dietCalories) {
        dietCaloriesService.update(dietCalories);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Integer id) {
        dietCaloriesService.delete(id);
    }
}
