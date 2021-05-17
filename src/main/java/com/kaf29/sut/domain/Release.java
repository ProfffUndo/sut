package com.kaf29.sut.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "trelease")
@ToString(of = {"id", "system_info_id", "release_version", "release_date","release_estimated_date","release_description"})
@EqualsAndHashCode(of = {"id"})
public class Release {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long system_info_id;

    private String  release_version;

    private Date release_date;

    private Date release_estimated_date;

    private String release_description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSystem_info_id() {
        return system_info_id;
    }

    public void setSystem_info_id(Long system_info_id) {
        this.system_info_id = system_info_id;
    }

    public String getRelease_version() {
        return release_version;
    }

    public void setRelease_version(String release_version) {
        this.release_version = release_version;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public Date getRelease_estimated_date() {
        return release_estimated_date;
    }

    public void setRelease_estimated_date(Date release_estimated_date) {
        this.release_estimated_date = release_estimated_date;
    }

    public String getRelease_description() {
        return release_description;
    }

    public void setRelease_description(String release_description) {
        this.release_description = release_description;
    }
}
