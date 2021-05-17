package com.kaf29.sut.domain;


import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ttestcasereport")
@ToString(of = {"id", "author", "creation_date", "success","testcase_id","testReport_id"})
@EqualsAndHashCode(of = {"id"})
public class TestCaseReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String author;

    private Date creation_date;

    private boolean success;

    private Long testcase_id;

    private Long testReport_id;

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

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Long getTestcase_id() {
        return testcase_id;
    }

    public void setTestcase_id(Long testcase_id) {
        this.testcase_id = testcase_id;
    }

    public Long getTestReport_id() {
        return testReport_id;
    }

    public void setTestReport_id(Long testReport_id) {
        this.testReport_id = testReport_id;
    }
}
