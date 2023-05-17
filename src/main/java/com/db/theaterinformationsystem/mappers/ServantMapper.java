package com.db.theaterinformationsystem.mappers;

import com.db.theaterinformationsystem.dto.ServantCreateDTO;
import com.db.theaterinformationsystem.dto.ServantDTO;
import com.db.theaterinformationsystem.model.Servant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CommonMapper.class)
public interface ServantMapper {

    ServantDTO map(Servant servant);

    Servant map(ServantDTO dto);

    @Mapping(target = "sex", ignore = true)
    @Mapping(target = "age", ignore = true)
    ServantCreateDTO mapServant(Servant servant);

    Servant map(ServantCreateDTO dto);
}