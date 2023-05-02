package com.db.theaterinformationsystem.mappers;

import com.db.theaterinformationsystem.dto.AudienceDTO;
import com.db.theaterinformationsystem.dto.GenreDTO;
import com.db.theaterinformationsystem.dto.PlayDTO;
import com.db.theaterinformationsystem.model.Audience;
import com.db.theaterinformationsystem.model.Genre;
import com.db.theaterinformationsystem.model.Play;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CommonMapper.class)
public interface PlayMapper {

    GenreDTO map(Genre genre);

    Genre map(GenreDTO dto);

    AudienceDTO map(Audience audience);

    Audience map(AudienceDTO dto);

    PlayDTO map(Play play);

    Play map(PlayDTO dto);
}
