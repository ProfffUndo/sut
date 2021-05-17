package com.kaf29.sut.domain;


import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "trequirement")
@ToString(of = {"id", "description", "element_id"})
@EqualsAndHashCode(of = {"id"})
public class Requirement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private Long element_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getElement_id() {
        return element_id;
    }

    public void setElement_id(Long element_id) {
        this.element_id = element_id;
    }
}
