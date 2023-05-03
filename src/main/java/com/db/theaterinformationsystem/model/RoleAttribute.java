package com.db.theaterinformationsystem.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "theatre", name = "role_attribute")
@IdClass(RoleAttribute.RoleAttributeId.class)
@Getter
@Setter
public class RoleAttribute {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role", nullable = false)
    private Role role;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attribute", nullable = false)
    private Attribute attribute;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "value", nullable = false)
    private AttributeValue value;

    @Data
    public static class RoleAttributeId implements Serializable {


        private Long role;
        private Long attribute;
        private Long value;

    }
}

