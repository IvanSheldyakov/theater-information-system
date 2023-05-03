package com.db.theaterinformationsystem.dto;

import lombok.Data;

@Data
public class ActorContestDTO {
    private Long actorId;
    private Long contestId;
    private Boolean winner;
}
