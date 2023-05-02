package com.db.theaterinformationsystem.mappers;

import com.db.theaterinformationsystem.dto.RoleDTO;
import com.db.theaterinformationsystem.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ActorMapper.class)
public interface RoleMapper {

    RoleDTO map(Role role);

    Role map(RoleDTO dto);
}