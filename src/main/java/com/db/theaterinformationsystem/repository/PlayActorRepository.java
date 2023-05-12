package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.Play;
import com.db.theaterinformationsystem.model.PlayActor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayActorRepository extends JpaRepository<PlayActor, Long> {

    List<PlayActor> findAllByPlay(Play play);
}
