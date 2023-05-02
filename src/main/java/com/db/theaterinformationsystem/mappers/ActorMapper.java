package com.db.theaterinformationsystem.mappers;

import com.db.theaterinformationsystem.dto.ActorDTO;
import com.db.theaterinformationsystem.model.Actor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CommonMapper.class)
public interface ActorMapper {


    ActorDTO map(Actor actor);

    Actor map(ActorDTO dto);
}
