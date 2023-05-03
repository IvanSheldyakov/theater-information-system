package com.db.theaterinformationsystem.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;


@Table(schema = "theatre", name = "actor_contest")
@IdClass(ActorContest.ActorContestId.class)
@Getter
@Setter
@Entity
public class ActorContest {


    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "actor", nullable = false)
    private Actor actor;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contest", nullable = false)
    private Contest contest;


    @Column(name = "winner", nullable = false)
    private Boolean winner;

    @Data
    public static class ActorContestId implements Serializable {

        private Long actor;
        private Long contest;

    }
}


