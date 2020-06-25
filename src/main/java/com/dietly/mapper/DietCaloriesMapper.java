package com.dietly.mapper;

import com.dietly.model.DietCalories;
import com.dietly.model.dto.DietCaloriesDto;
import com.dietly.model.requests.AddDietCaloriesToDietOptionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DietCaloriesMapper {
    DietCalories createDietCaloriesFromDto(DietCaloriesDto dto);

    @Mappings(value = {
            @Mapping(source = "calories", target = "calories"),
            @Mapping(target = "dietCaloriesId", ignore = true)
    })
    DietCalories createDietCaloriesFromDto(AddDietCaloriesToDietOptionRequest dto);
}
