package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleAttributeRepository extends JpaRepository<RoleAttribute, Long> {

    RoleAttribute save(RoleAttribute attribute);

    Optional<RoleAttribute> findByAttributeAndRoleAndValue(Attribute attribute, Role role, AttributeValue value);
}
