package com.kaf29.sut.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ttestcase")
@ToString(of = {"id", "author", "update_date", "creation_date", "case_name", "step", "input_data", "result", "chain_id", "testplan_id", "element_id"})
@EqualsAndHashCode(of = {"id"})
public class TestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String author;

    private Date update_date;

    private Date creation_date;

    private String case_name;

    private String step;

    private String input_data;

    private String result;

    private Long chain_id;

    private Long testplan_id;

    private Long element_id;


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

    public String getCase_name() {
        return case_name;
    }

    public void setCase_name(String case_name) {
        this.case_name = case_name;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getInput_data() {
        return input_data;
    }

    public void setInput_data(String input_data) {
        this.input_data = input_data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getChain_id() {
        return chain_id;
    }

    public void setChain_id(Long chain_id) {
        this.chain_id = chain_id;
    }

    public Long getTestplan_id() {
        return testplan_id;
    }

    public void setTestplan_id(Long testplan_id) {
        this.testplan_id = testplan_id;
    }

    public Long getElement_id() {
        return element_id;
    }

    public void setElement_id(Long element_id) {
        this.element_id = element_id;
    }
}
