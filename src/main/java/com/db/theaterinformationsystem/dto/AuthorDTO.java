package com.db.theaterinformationsystem.dto;

import lombok.Data;

@Data
public class AuthorDTO {

    private Long id;
    private FullNameDTO fullName;
    private Integer century;
    private String country;
}
