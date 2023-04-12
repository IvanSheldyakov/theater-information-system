package db.theatre.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(schema = "theatre", name = "musician")
@Data
public class Musician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee", nullable = false, unique = true)
    private Employee employee;

}

