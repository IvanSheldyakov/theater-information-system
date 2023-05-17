package com.db.theaterinformationsystem.dto;

import lombok.Data;

@Data
public class MusicianCreateDTO {

    private Long id;
    private EmployeeDTO employee;
    private String sex;
    private String age;
}
