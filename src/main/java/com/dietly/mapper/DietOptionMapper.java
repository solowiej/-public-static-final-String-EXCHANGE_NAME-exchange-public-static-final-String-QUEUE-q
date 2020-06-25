package com.dietly.mapper;

import com.dietly.model.DietOption;
import com.dietly.model.dto.DietOptionDto;
import com.dietly.model.requests.AddDietOptionToDietRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DietOptionMapper {
    DietOption createDietOptionFromDto(DietOptionDto dietOptionDto);

    @Mappings(value = {
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "abbreviation", target = "abbreviation"),
            @Mapping(target = "dietOptionId", ignore = true)
    })
    DietOption createDietOptionFromDto(AddDietOptionToDietRequest addDietOptionToDietRequest);
}
