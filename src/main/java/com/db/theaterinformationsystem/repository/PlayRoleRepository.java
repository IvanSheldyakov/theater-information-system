package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.Play;
import com.db.theaterinformationsystem.model.PlayRole;
import com.db.theaterinformationsystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayRoleRepository extends JpaRepository<PlayRole, Long> {

    Optional<PlayRole> findByPlayAndRole(Play play, Role role);

    List<PlayRole> findAllByPlay(Play play);
}
