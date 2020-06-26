package com.dietly.service;

import com.dietly.exception.WrongOperation;
import com.dietly.mapper.DietCaloriesMapper;
import com.dietly.mapper.DietOptionMapper;
import com.dietly.model.DietCalories;
import com.dietly.model.DietOption;
import com.dietly.model.dto.DietOptionDto;
import com.dietly.model.requests.AddDietCaloriesToDietOptionRequest;
import com.dietly.model.requests.AssignDietCaloriesToDietOption;
import com.dietly.repository.DietCaloriesRepository;
import com.dietly.repository.DietOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class DietOptionService {
    private DietOptionRepository dietOptionRepository;
    private DietOptionMapper dietOptionMapper;
    private DietCaloriesRepository dietCaloriesRepository;
    private DietCaloriesMapper dietCaloriesMapper;

    @Autowired
    public DietOptionService(DietOptionRepository dietOptionRepository, DietOptionMapper dietOptionMapper, DietCaloriesRepository dietCaloriesRepository, DietCaloriesMapper dietCaloriesMapper) {
        this.dietOptionRepository = dietOptionRepository;
        this.dietOptionMapper = dietOptionMapper;
        this.dietCaloriesRepository = dietCaloriesRepository;
        this.dietCaloriesMapper = dietCaloriesMapper;
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

    public void save(DietOptionDto dto) {
        DietOption dietOption = dietOptionMapper.createDietOptionFromDto(dto);
        dietOptionRepository.save(dietOption);
    }

    public void update(DietOptionDto dto) {
        Optional<DietOption> optionalDietOption = dietOptionRepository.findById(dto.getDietOptionId());

        if (optionalDietOption.isPresent()) {
            DietOption dietOption = optionalDietOption.get();

            if (dietOption.getName() != null) {
                dietOption.setName(dto.getName());
            }

            if (dietOption.getAbbreviation() != null) {
                dietOption.setAbbreviation(dto.getAbbreviation());
            }

            dietOptionRepository.save(dietOption);
            return;
        }
        throw new EntityNotFoundException("dietOption, id:" + dto.getDietOptionId());
    }

    public void delete(Integer id) {
        if (dietOptionRepository.existsById(id)) {
            dietOptionRepository.deleteById(id);
            return;
        }
        throw new EntityNotFoundException("dietOption, id:" + id);
    }

    public void addDietCaloriesToDietOption(AddDietCaloriesToDietOptionRequest dto) {
        Optional<DietOption> dietOptionOptional = dietOptionRepository.findById(dto.getDietOptionId());

        if (dietOptionOptional.isPresent()) {
            DietOption dietOption = dietOptionOptional.get();

            DietCalories dietCalories = dietCaloriesMapper.createDietCaloriesFromDto(dto);
            dietCalories.setDietOption(dietOption);

            dietCaloriesRepository.save(dietCalories);
        } else {
            throw new EntityNotFoundException("dietOption, id:" + dto.getDietOptionId());
        }
    }

    public void assignDietCaloriesToDietOption(AssignDietCaloriesToDietOption dto) {
        Optional<DietOption> dietOptionOptional = dietOptionRepository.findById(dto.getDietOptionId());
        if (!dietOptionOptional.isPresent()) {
            throw new EntityNotFoundException("dietOption, id:" + dto.getDietOptionId());
        }
        Optional<DietCalories> dietCaloriesOptional = dietCaloriesRepository.findById(dto.getDietCaloriesId());
        if (!dietCaloriesOptional.isPresent()) {
            throw new EntityNotFoundException("dietCalories, id" + dto.getDietCaloriesId());
        }

        DietOption dietOption = dietOptionOptional.get();
        DietCalories dietCalories = dietCaloriesOptional.get();

        if (dietCalories.getDietOption() != null) {
            throw new WrongOperation("You should not assign dietCalories that is already assigned.");
        }

        dietCalories.setDietOption(dietOption);
        dietCaloriesRepository.save(dietCalories);
    }
}
