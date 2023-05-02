package com.db.theaterinformationsystem.mappers;

import com.db.theaterinformationsystem.dto.ServantDTO;
import com.db.theaterinformationsystem.model.Servant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CommonMapper.class)
public interface ServantMapper {

    ServantDTO map(Servant servant);

    Servant map(ServantDTO dto);
}