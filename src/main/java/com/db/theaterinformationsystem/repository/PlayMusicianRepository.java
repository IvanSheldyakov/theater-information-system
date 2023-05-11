package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.Musician;
import com.db.theaterinformationsystem.model.Play;
import com.db.theaterinformationsystem.model.PlayMusician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayMusicianRepository extends JpaRepository<PlayMusician, Long> {

    Optional<PlayMusician> findByPlayAndMusician(Play play, Musician musician);
}
