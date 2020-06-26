package com.dietly.controller;


import com.dietly.model.dto.DietCaloriesDto;
import com.dietly.service.DietCaloriesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(DietCaloriesController.API_DIET_CALORIES)
@AllArgsConstructor
public class DietCaloriesController {
    static final String API_DIET_CALORIES = "/api/dietCalories";

    @Autowired
    private DietCaloriesService dietCaloriesService;


    @GetMapping()
    public ResponseEntity getDietCaloriesList() {
        return ResponseEntity.ok(dietCaloriesService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Integer dietCaloriesId) {
        return ResponseEntity.ok(dietCaloriesService.getById(dietCaloriesId));
    }

    @PostMapping
    public ResponseEntity postDietCalories(@RequestBody DietCaloriesDto dto) {
        return ResponseEntity.ok(dietCaloriesService.save(dto));
    }

    @PutMapping
    public ResponseEntity putDietCalories(@RequestBody DietCaloriesDto dto) {
        return ResponseEntity.ok(dietCaloriesService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(dietCaloriesService.delete(id));
    }
}
