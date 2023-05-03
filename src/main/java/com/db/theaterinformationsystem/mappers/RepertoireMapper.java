package com.db.theaterinformationsystem.mappers;

import com.db.theaterinformationsystem.dto.RepertoireDTO;
import com.db.theaterinformationsystem.model.Repertoire;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PlayMapper.class)
public interface RepertoireMapper {

    RepertoireDTO map(Repertoire repertoire);

    Repertoire map(RepertoireDTO dto);
}
