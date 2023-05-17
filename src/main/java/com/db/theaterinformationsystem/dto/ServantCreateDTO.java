package com.db.theaterinformationsystem.dto;

import lombok.Data;

@Data
public class ServantCreateDTO {

    private Long id;
    private EmployeeDTO employee;
    private String sex;
    private String age;
}
