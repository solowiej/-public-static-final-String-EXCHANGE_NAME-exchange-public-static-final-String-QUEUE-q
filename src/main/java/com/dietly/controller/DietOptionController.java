package com.dietly.controller;

import com.dietly.model.dto.DietOptionDto;
import com.dietly.model.requests.AddDietCaloriesToDietOptionRequest;
import com.dietly.model.requests.AssignDietCaloriesToDietOption;
import com.dietly.service.DietOptionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(DietOptionController.API_DIET_OPTION_PATH)
@AllArgsConstructor
public class DietOptionController {
    static final String API_DIET_OPTION_PATH = "/api/dietOption";

    @Autowired
    private DietOptionService dietOptionService;

    @GetMapping()
    public ResponseEntity getDietOptionList() {
        return ResponseEntity.ok(dietOptionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Integer dietOptionId) {
        return ResponseEntity.ok(dietOptionService.getById(dietOptionId));
    }

    @PostMapping
    public ResponseEntity postDietOption(@RequestBody DietOptionDto dto) {
        return ResponseEntity.ok(dietOptionService.save(dto));
    }

    @PutMapping
    public ResponseEntity putDietOption(@RequestBody DietOptionDto dto) {
        return ResponseEntity.ok(dietOptionService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(dietOptionService.delete(id));
    }

    @PostMapping("/dietCalories")
    public ResponseEntity addDietCalories(@RequestBody AddDietCaloriesToDietOptionRequest dto) {
        return ResponseEntity.ok(dietOptionService.addDietCaloriesToDietOption(dto));
    }

    @PostMapping("/assignDietCalories")
    public ResponseEntity assignDietCalories(@RequestBody AssignDietCaloriesToDietOption dto) {
        return ResponseEntity.ok(dietOptionService.assignDietCaloriesToDietOption(dto));
    }
}
