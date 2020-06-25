package com.dietly.controller;

import com.dietly.model.Diet;
import com.dietly.service.DietService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(DietController.API_DIET_PATH)
@AllArgsConstructor
public class DietController {
    static final String API_DIET_PATH = "/api/diet";

    @Autowired
    private DietService dietService;

    @GetMapping()
    public List<Diet> getDietList() {
        return dietService.getAll();
    }

    @GetMapping("/{id}")
    public Diet getById(@PathVariable("id") Integer dietId) {
        return dietService.getById(dietId);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void postDiet(Diet diet) {
        dietService.save(diet);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void putDiet(Diet diet) {
        dietService.update(diet);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Integer id) {
        dietService.delete(id);
    }
}
