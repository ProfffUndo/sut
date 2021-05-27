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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public Long getRelease_id() {
        return release_id;
    }

    public void setRelease_id(Long release_id) {
        this.release_id = release_id;
    }
}
