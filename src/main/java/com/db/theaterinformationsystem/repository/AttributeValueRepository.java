package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.Attribute;
import com.db.theaterinformationsystem.model.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttributeValueRepository extends JpaRepository<AttributeValue, Long> {

    Optional<AttributeValue> findByAttributeAndValue(Attribute attribute, String value);

    AttributeValue save(AttributeValue value);
}
