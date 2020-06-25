package com.dietly.controller;

import com.dietly.model.Diet;
import com.dietly.model.dto.DietDto;
import com.dietly.model.requests.AddDietOptionToDietRequest;
import com.dietly.model.requests.AssignDietOptionToDiet;
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
    public void postDiet(@RequestBody DietDto dto) {
        dietService.save(dto);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void putDiet(@RequestBody DietDto dto) {
        dietService.update(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Integer id) {
        dietService.delete(id);
    }
    @PostMapping("/dietOption")
    public Integer addDietOption(AddDietOptionToDietRequest dto) {
        return dietService.addDietOptionToDiet(dto);
    }

    @PostMapping("/assignDietOption")
    public Integer addStudent(AssignDietOptionToDiet dto) {
        return dietService.assingDietOptionToDiet(dto);
    }

}
