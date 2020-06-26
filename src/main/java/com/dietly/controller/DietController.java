package com.dietly.controller;

import com.dietly.model.dto.DietDto;
import com.dietly.model.requests.AddDietOptionToDietRequest;
import com.dietly.model.requests.AssignDietOptionToDiet;
import com.dietly.service.DietService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(DietController.API_DIET_PATH)
@AllArgsConstructor
public class DietController {
    static final String API_DIET_PATH = "/api/diet";

    @Autowired
    private DietService dietService;

    @GetMapping()
    public ResponseEntity getDietList() {
        return ResponseEntity.ok(dietService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Integer dietId) {
        return ResponseEntity.ok(dietService.getById(dietId));
    }

    @PostMapping
    public ResponseEntity postDiet(@RequestBody DietDto dto) {
        return ResponseEntity.ok(dietService.save(dto));
    }

    @PutMapping
    public ResponseEntity putDiet(@RequestBody DietDto dto) {
        return ResponseEntity.ok(dietService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(dietService.delete(id));
    }

    @PostMapping("/dietOption")
    public ResponseEntity addDietOption(@RequestBody AddDietOptionToDietRequest dto) {
        return ResponseEntity.ok(dietService.addDietOptionToDiet(dto));
    }

    @PostMapping("/assignDietOption")
    public ResponseEntity assignDietOption(@RequestBody AssignDietOptionToDiet dto) {
        return ResponseEntity.ok(dietService.assingDietOptionToDiet(dto));
    }
}
