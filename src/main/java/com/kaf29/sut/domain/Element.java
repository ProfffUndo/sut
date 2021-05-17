package com.kaf29.sut.domain;


import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tcl_element")
@ToString(of = {"id", "author", "update_date", "creation_date","release_id"})
@EqualsAndHashCode(of = {"id"})
public class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String element_name;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getElement_name() {
        return element_name;
    }

    public void setElement_name(String element_name) {
        this.element_name = element_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
