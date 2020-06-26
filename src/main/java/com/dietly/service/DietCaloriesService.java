package com.dietly.service;

import com.dietly.mapper.DietCaloriesMapper;
import com.dietly.model.DietCalories;
import com.dietly.model.dto.DietCaloriesDto;
import com.dietly.repository.DietCaloriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class DietCaloriesService {
    private DietCaloriesRepository dietCaloriesRepository;
    private DietCaloriesMapper dietCaloriesMapper;

    @Autowired
    public DietCaloriesService(DietCaloriesRepository dietCaloriesRepository, DietCaloriesMapper dietCaloriesMapper) {
        this.dietCaloriesRepository = dietCaloriesRepository;
        this.dietCaloriesMapper = dietCaloriesMapper;
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

    public DietCalories save(DietCaloriesDto dto) {
        DietCalories dietCalories = dietCaloriesMapper.createDietCaloriesFromDto(dto);
        return dietCaloriesRepository.save(dietCalories);
    }

    public DietCalories update(DietCaloriesDto dto) {
        Optional<DietCalories> optionalDietCalories = dietCaloriesRepository.findById(dto.getDietCaloriesId());

        if (optionalDietCalories.isPresent()) {
            DietCalories dietCalories = optionalDietCalories.get();

            if (dietCalories.getCalories() != null) {
                dietCalories.setCalories(dto.getCalories());
            }

            return dietCaloriesRepository.save(dietCalories);
        }
        throw new EntityNotFoundException("dietCalories, id:" + dto.getDietCaloriesId());
    }

    public DietCalories delete(Integer id) {
        Optional<DietCalories> optionalDietCalories = dietCaloriesRepository.findById(id);

        if (optionalDietCalories.isPresent()) {
            DietCalories dietCalories = optionalDietCalories.get();
            dietCaloriesRepository.delete(dietCalories);
            return dietCalories;
        }
        throw new EntityNotFoundException("dietCalories, id:" + id);
    }
}
