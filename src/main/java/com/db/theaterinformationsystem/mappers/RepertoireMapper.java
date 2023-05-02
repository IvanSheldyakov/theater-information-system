package com.db.theaterinformationsystem.mappers;

import com.db.theaterinformationsystem.dto.RepertoireDTO;
import com.db.theaterinformationsystem.model.Repertoire;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RepertoireMapper {

    RepertoireDTO map(Repertoire repertoire);

    @Mapping(target = "play", ignore = true)
    Repertoire map(RepertoireDTO dto);
}
