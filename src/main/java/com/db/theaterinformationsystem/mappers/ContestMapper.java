package com.db.theaterinformationsystem.mappers;

import com.db.theaterinformationsystem.dto.ContestDTO;
import com.db.theaterinformationsystem.model.Contest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CommonMapper.class)
public interface ContestMapper {

    ContestDTO map(Contest contest);

    Contest map(ContestDTO dto);
}