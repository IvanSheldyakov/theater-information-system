package com.db.theaterinformationsystem.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "theatre", name = "play_actor")
@IdClass(PlayActor.PlayActorId.class)
@Getter
@Setter
public class PlayActor {

    @Id
    @ManyToOne
    @JoinColumn(name = "play", nullable = false)
    private Play play;

    @Id
    @ManyToOne
    @JoinColumn(name = "actor", nullable = false)
    private Actor actor;

    @Data
    public static class PlayActorId implements Serializable {

        private Long play;
        private Long actor;

    }
}

