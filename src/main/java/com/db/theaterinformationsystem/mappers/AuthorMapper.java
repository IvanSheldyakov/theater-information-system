package com.db.theaterinformationsystem.mappers;

import com.db.theaterinformationsystem.dto.AuthorDTO;
import com.db.theaterinformationsystem.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CommonMapper.class)
public interface AuthorMapper {

    AuthorDTO map(Author author);

    Author map(AuthorDTO dto);
}
