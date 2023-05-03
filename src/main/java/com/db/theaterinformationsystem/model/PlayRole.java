package com.db.theaterinformationsystem.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "play_role", schema = "theatre")
@IdClass(PlayRole.PlayRoleId.class)
public class PlayRole {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "play")
    private Play play;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role", nullable = false)
    private Role role;

    @Data
    public static class PlayRoleId implements Serializable {

        private Long play;
        private Long role;

    }
}

