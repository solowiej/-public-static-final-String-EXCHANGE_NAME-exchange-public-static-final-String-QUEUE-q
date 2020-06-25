package com.dietly.service;

import com.dietly.model.DietOption;
import com.dietly.repository.DietOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class DietOptionService {

    private DietOptionRepository dietOptionRepository;

    @Autowired
    public DietOptionService(DietOptionRepository dietOptionRepository) {
        this.dietOptionRepository = dietOptionRepository;
    }

    public List<DietOption> getAll() {
        return dietOptionRepository.findAll();
    }


    public DietOption getById(Integer dietOptionId) {
        Optional<DietOption> optionalDietOption = dietOptionRepository.findById(dietOptionId);

        if (optionalDietOption.isPresent()) {
            return optionalDietOption.get();
        }
        throw new EntityNotFoundException("dietOption, id:" + dietOptionId);
    }

    public void save(DietOption dietOption) {
        dietOptionRepository.save(dietOption);
    }

    public void update(DietOption dietOption) {
        Optional<DietOption> optionalDietOption = dietOptionRepository.findById(dietOption.getDietOptionId());

        if (optionalDietOption.isPresent()) {
            dietOption = optionalDietOption.get();

            if (dietOption.getName() != null) {
                dietOption.setName(dietOption.getName());
            }

            if (dietOption.getAbbreviation() != null) {
                dietOption.setAbbreviation(dietOption.getAbbreviation());
            }


            //need to check this one
            if (dietOption.getDiet() != null) {
                dietOption.setDiet(dietOption.getDiet());
            }

            if (!dietOption.getDietCalories().isEmpty()) {
                dietOption.setDietCalories(dietOption.getDietCalories());
            }
            //

            dietOptionRepository.save(dietOption);
            return;
        }
        throw new EntityNotFoundException("dietOption, id:" + dietOption.getDietOptionId());
    }

    public void delete(Integer id) {
        if (dietOptionRepository.existsById(id)) {
            dietOptionRepository.deleteById(id);
            return;
        }
        throw new EntityNotFoundException("dietOption, id:" + id);
    }
}
