package com.db.theaterinformationsystem.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployeeDTO {

    private FullNameDTO fullNameDTO;
    private String standing;
    private Integer birthYear;
    private Boolean children;
    private Integer childrenNumber;
    private BigDecimal payment;
    private CategoryDTO categoryDTO;
}
