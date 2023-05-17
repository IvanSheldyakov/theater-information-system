package com.db.theaterinformationsystem.mappers;

import com.db.theaterinformationsystem.dto.MusicianCreateDTO;
import com.db.theaterinformationsystem.dto.MusicianDTO;
import com.db.theaterinformationsystem.model.Musician;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CommonMapper.class)
public interface MusicianMapper {

    MusicianDTO map(Musician musician);

    Musician map(MusicianDTO dto);

    @Mapping(target = "sex", ignore = true)
    @Mapping(target = "age", ignore = true)
    MusicianCreateDTO mapMusician(Musician musician);

    Musician map(MusicianCreateDTO dto);
}