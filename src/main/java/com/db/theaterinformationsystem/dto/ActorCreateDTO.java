package com.db.theaterinformationsystem.dto;

import lombok.Data;

@Data
public class ActorCreateDTO {

    private Long id;
    private EmployeeDTO employee;
    private Boolean honoredArtist;
    private Boolean nationalArtist;
    private Boolean student;
    private String sex;
    private String age;
}
