package com.dietly.controller;

import com.dietly.model.DietOption;
import com.dietly.service.DietOptionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(DietOptionController.API_DIET_OPTION_PATH)
@AllArgsConstructor
public class DietOptionController {
    static final String API_DIET_OPTION_PATH = "/api/dietOption";

    @Autowired
    private DietOptionService dietOptionService;

    @GetMapping()
    public List<DietOption> getDietOptionList() {
        return dietOptionService.getAll();
    }

    @GetMapping("/{id}")
    public DietOption getById(@PathVariable("id") Integer dietOptionId) {
        return dietOptionService.getById(dietOptionId);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void postDietOption(DietOption dietOption) {
        dietOptionService.save(dietOption);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void putDietOption(DietOption dietOption) {
        dietOptionService.update(dietOption);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Integer id) {
        dietOptionService.delete(id);
    }
}
