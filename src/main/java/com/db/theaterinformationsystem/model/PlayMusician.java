package com.db.theaterinformationsystem.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "theatre", name = "play_musician")
@IdClass(PlayMusician.PlayMusicianId.class)
@Getter
@Setter
public class PlayMusician {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "play", nullable = false)
    private Play play;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "musician", nullable = false)
    private Musician musician;

    @Data
    public static class PlayMusicianId implements Serializable {

        private Long play;
        private Long musician;

    }
}

