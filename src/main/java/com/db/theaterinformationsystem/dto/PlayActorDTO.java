package com.db.theaterinformationsystem.dto;

import lombok.Data;

@Data
public class PlayActorDTO {
    private Long actorId;
    private Long playId;
    private Long roleId;
    private Boolean backup;
}
