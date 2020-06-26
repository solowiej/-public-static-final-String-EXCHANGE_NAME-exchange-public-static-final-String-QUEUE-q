package com.dietly.service;

import com.dietly.exception.WrongOperation;
import com.dietly.mapper.DietMapper;
import com.dietly.mapper.DietOptionMapper;
import com.dietly.model.Diet;
import com.dietly.model.DietOption;
import com.dietly.model.dto.DietDto;
import com.dietly.model.requests.AddDietOptionToDietRequest;
import com.dietly.model.requests.AssignDietOptionToDiet;
import com.dietly.repository.DietOptionRepository;
import com.dietly.repository.DietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class DietService {
    private DietRepository dietRepository;
    private DietMapper dietMapper;
    private DietOptionRepository dietOptionRepository;
    private DietOptionMapper dietOptionMapper;


    @Autowired
    public DietService(DietRepository dietRepository, DietMapper dietMapper, DietOptionRepository dietOptionRepository, DietOptionMapper dietOptionMapper) {
        this.dietRepository = dietRepository;
        this.dietMapper = dietMapper;
        this.dietOptionRepository = dietOptionRepository;
        this.dietOptionMapper = dietOptionMapper;
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

    public Diet save(DietDto dto) {
        Diet diet = dietMapper.createDietFromDto(dto);
        return dietRepository.save(diet);
    }

    public Diet update(DietDto dto) {
        Optional<Diet> optionalDiet = dietRepository.findById(dto.getDietId());

        if (optionalDiet.isPresent()) {
            Diet diet = optionalDiet.get();

            if (diet.getName() != null) {
                diet.setName(dto.getName());
            }
            if (diet.getDescription() != null) {
                diet.setDescription(dto.getDescription());
            }

            return dietRepository.save(diet);
        }
        throw new EntityNotFoundException("diet, id:" + dto.getDietId());
    }

    public Diet delete(Integer id) {
        Optional<Diet> optionalDiet = dietRepository.findById(id);
        if (optionalDiet.isPresent()) {
            Diet diet = optionalDiet.get();
            dietRepository.deleteById(id);
            return diet;
        }
        throw new EntityNotFoundException("diet, id:" + id);
    }

    public DietOption addDietOptionToDiet(AddDietOptionToDietRequest addDietOptionToDietRequest) {
        Optional<Diet> optionalDiet = dietRepository.findById(addDietOptionToDietRequest.getDietId());

        if (optionalDiet.isPresent()) {
            Diet diet = optionalDiet.get();

            DietOption dietOption = dietOptionMapper.createDietOptionFromDto(addDietOptionToDietRequest);
            dietOption.setDiet(diet);

            return dietOptionRepository.save(dietOption);
        } else {
            throw new EntityNotFoundException("diet, id:" + addDietOptionToDietRequest.getDietId());
        }
    }

    public DietOption assingDietOptionToDiet(AssignDietOptionToDiet dto) {
        Optional<Diet> optionalDiet = dietRepository.findById(dto.getDietId());
        if (!optionalDiet.isPresent()) {
            throw new EntityNotFoundException("diet, id:" + dto.getDietId());
        }
        Optional<DietOption> optionalDietOption = dietOptionRepository.findById(dto.getDietOptionId());
        if (!optionalDietOption.isPresent()) {
            throw new EntityNotFoundException("dietOption , id:" + dto.getDietOptionId());
        }

        Diet diet = optionalDiet.get();
        DietOption dietOption = optionalDietOption.get();

        if (dietOption.getDiet() != null) {
            throw new WrongOperation("You should not assign dietOption that is already assigned.");
        }

        dietOption.setDiet(diet);
        return dietOptionRepository.save(dietOption);
    }
}
