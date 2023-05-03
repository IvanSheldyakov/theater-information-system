package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.ActorContest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorContestRepository extends JpaRepository<ActorContest, Long> {

}
