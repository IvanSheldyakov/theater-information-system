package com.db.theaterinformationsystem.dto;

import lombok.Data;

@Data
public class RoleCreateDTO {

    private Long id;
    private Boolean main;
    private String description;
}
