package com.db.theaterinformationsystem.dto;

import lombok.Data;

@Data
public class ActorDTO {

    private EmployeeDTO employeeDTO;
    private Boolean honoredArtist;
    private Boolean nationalArtist;
    private Boolean student;
}
