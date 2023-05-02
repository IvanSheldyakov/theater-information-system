package com.db.theaterinformationsystem.dto;

import lombok.Data;

@Data
public class RoleDTO {

    private Long id;
    private ActorDTO actor;
    private Boolean main;
    private ActorDTO backup;
}
