package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.Servant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServantRepository extends JpaRepository<Servant, Long> {

    Servant save(Servant servant);
}
