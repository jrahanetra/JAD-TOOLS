package com.jadteam.jadapi.teacher;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * teacher
 */
public class Teacher {
    
	@Id
    @GeneratedValue
    private Integer id;
    private String firstname;
    private String lastname;

    public Teacher() {
    }

    public Teacher(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
