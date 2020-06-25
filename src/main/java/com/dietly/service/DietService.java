package com.dietly.service;

import com.dietly.model.Diet;
import com.dietly.repository.DietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class DietService {
    private DietRepository dietRepository;

    @Autowired
    public DietService(DietRepository dietRepository) {
        this.dietRepository = dietRepository;
    }

    public List<Diet> getAll() {
        return dietRepository.findAll();
    }


    public Diet getById(Integer dietId) {
        Optional<Diet> optionalDiet = dietRepository.findById(dietId);

        if (optionalDiet.isPresent()) {
            return optionalDiet.get();
        }
        throw new EntityNotFoundException("diet, id:" + dietId);
    }

    public void save(Diet diet) {
        dietRepository.save(diet);
    }

    public void update(Diet diet) {
        Optional<Diet> optionalDiet = dietRepository.findById(diet.getDietId());

        if (optionalDiet.isPresent()) {
            diet = optionalDiet.get();

            if (diet.getName() != null) {
                diet.setName(diet.getName());
            }
            if (diet.getDescription() != null) {
                diet.setDescription(diet.getDescription());
            }


            //to check
            if (!diet.getDietOptions().isEmpty()) {
                diet.setDietOptions(diet.getDietOptions());
            }

            dietRepository.save(diet);
            return;
        }
        throw new EntityNotFoundException("diet, id:" + diet.getDietId());
    }

    public void delete(Integer id) {
        if (dietRepository.existsById(id)) {
            dietRepository.deleteById(id);
            return;
        }
        throw new EntityNotFoundException("diet, id:" + id);
    }
}
