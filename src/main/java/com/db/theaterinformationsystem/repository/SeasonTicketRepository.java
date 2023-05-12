package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.SeasonTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeasonTicketRepository extends JpaRepository<SeasonTicket, Long> {

    Optional<SeasonTicket> findById(Long id);

    SeasonTicket save(SeasonTicket seasonTicket);

}
