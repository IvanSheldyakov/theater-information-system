package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {

    Producer save(Producer producer);
}
