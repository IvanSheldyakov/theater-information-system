package com.db.theaterinformationsystem.mappers;

import com.db.theaterinformationsystem.dto.ActorDTO;
import com.db.theaterinformationsystem.model.Actor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CommonMapper.class)
public interface ActorMapper {

    //@Mapping(target = "honoredArtist", source = "honoredArtist")
    ActorDTO map(Actor actor);

  //  @Mapping(target = "id", ignore = true)
    Actor map(ActorDTO dto);
}
