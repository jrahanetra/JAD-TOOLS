package com.jadteam.jadapi.subject;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Subject
 */
public class Subject {
    
	@Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer hourNumber;

    public Subject() {
    }

    public Subject(String name, Integer hourNumber) {
        this.name = name;
        this.hourNumber = hourNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHourNumber() {
        return hourNumber;
    }

    public void setHourNumber(Integer hourNumber) {
        this.hourNumber = hourNumber;
    }

}
