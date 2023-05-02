package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.Audience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudienceRepository extends JpaRepository<Audience, Long> {

    Audience save(Audience audience);
}
