package com.db.theaterinformationsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CategoryDTO {

    private Long id;
    private String name;

}
