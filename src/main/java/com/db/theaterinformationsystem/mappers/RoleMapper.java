package com.db.theaterinformationsystem.mappers;

import com.db.theaterinformationsystem.dto.RoleDTO;
import com.db.theaterinformationsystem.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ActorMapper.class)
public interface RoleMapper {

    RoleDTO map(Role role);

    @Mapping(target = "actor", ignore = true)
    @Mapping(target = "backup", ignore = true)
    Role map(RoleDTO dto);
}