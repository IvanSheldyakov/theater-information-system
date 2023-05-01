package com.db.theaterinformationsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActorProjection {
    private String surname;
    private String name;
    private String patronymic;
}