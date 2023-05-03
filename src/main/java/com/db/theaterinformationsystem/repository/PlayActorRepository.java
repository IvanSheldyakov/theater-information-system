package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.PlayActor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayActorRepository extends JpaRepository<PlayActor, Long> {
}
