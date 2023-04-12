package db.theatre.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "theatre", name = "actor_contest")
@IdClass(ActorContest.ActorContestId.class)
@Getter
@Setter
public class ActorContest {


    @Id
    @ManyToOne
    @JoinColumn(name = "actor", nullable = false)
    private Actor actor;

    @Id
    @ManyToOne
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


