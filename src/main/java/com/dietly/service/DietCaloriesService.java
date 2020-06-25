package com.dietly.service;

import com.dietly.model.DietCalories;
import com.dietly.repository.DietCaloriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class DietCaloriesService {
    private DietCaloriesRepository dietCaloriesRepository;

    @Autowired
    public DietCaloriesService(DietCaloriesRepository dietCaloriesRepository) {
        this.dietCaloriesRepository = dietCaloriesRepository;
    }

    public List<DietCalories> getAll() {
        return dietCaloriesRepository.findAll();
    }


    public DietCalories getById(Integer dietCaloriesId) {
        Optional<DietCalories> optionalDietCalories = dietCaloriesRepository.findById(dietCaloriesId);

        if (optionalDietCalories.isPresent()) {
            return optionalDietCalories.get();
        }
        throw new EntityNotFoundException("dietCalories, id:" + dietCaloriesId);
    }

    public void save(DietCalories dietCalories) {
        dietCaloriesRepository.save(dietCalories);
    }

    public void update(DietCalories dietCalories) {
        Optional<DietCalories> optionalDietCalories = dietCaloriesRepository.findById(dietCalories.getDietCaloriesId());

        if (optionalDietCalories.isPresent()) {
            dietCalories = optionalDietCalories.get();

            if (dietCalories.getCalories() != null) {
                dietCalories.setCalories(dietCalories.getCalories());
            }


            //to check
            if (dietCalories.getDietOption() != null) {
                dietCalories.setDietOption(dietCalories.getDietOption());
            }


            dietCaloriesRepository.save(dietCalories);
            return;
        }
        throw new EntityNotFoundException("dietCalories, id:" + dietCalories.getDietCaloriesId());
    }

    public void delete(Integer id) {
        if (dietCaloriesRepository.existsById(id)) {
            dietCaloriesRepository.deleteById(id);
            return;
        }
        throw new EntityNotFoundException("dietCalories, id:" + id);
    }
}
