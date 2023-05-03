package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.PlayMusician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayMusicianRepository extends JpaRepository<PlayMusician, Long> {
}
