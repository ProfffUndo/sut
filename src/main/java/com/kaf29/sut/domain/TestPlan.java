package com.kaf29.sut.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ttestplan")
@ToString(of = {"id", "author", "update_date", "creation_date","release_id"})
@EqualsAndHashCode(of = {"id"})
public class TestPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String author;

    private Date update_date;

    private Date creation_date;

    private Long release_id;

}
