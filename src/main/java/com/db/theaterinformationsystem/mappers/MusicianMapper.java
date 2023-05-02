package com.db.theaterinformationsystem.mappers;

import com.db.theaterinformationsystem.dto.MusicianDTO;
import com.db.theaterinformationsystem.model.Musician;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CommonMapper.class)
public interface MusicianMapper {

    MusicianDTO map(Musician musician);

    Musician map(MusicianDTO dto);
}