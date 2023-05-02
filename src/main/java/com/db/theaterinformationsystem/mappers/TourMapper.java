package com.db.theaterinformationsystem.mappers;

import com.db.theaterinformationsystem.dto.TourDTO;
import com.db.theaterinformationsystem.model.Tour;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CommonMapper.class)
public interface TourMapper {

    TourDTO map(Tour tour);

    @Mapping(target = "play", ignore = true)
    Tour map(TourDTO dto);
}