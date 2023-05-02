package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Long> {

    Contest save(Contest contest);
}
