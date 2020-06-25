package com.dietly.mapper;

import com.dietly.model.Diet;
import com.dietly.model.dto.DietDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DietMapper {
    Diet createDietFromDto(DietDto dto);
}
