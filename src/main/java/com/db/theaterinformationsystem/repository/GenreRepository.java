package com.db.theaterinformationsystem.repository;


import com.db.theaterinformationsystem.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre save(Genre genre);
}
