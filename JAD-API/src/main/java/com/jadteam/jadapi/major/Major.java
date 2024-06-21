package com.jadteam.jadapi.major;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * major
 */
@Entity
@Table
public class Major {
    
	@Id
    @GeneratedValue
    private Integer id;
    private String name;

    public Major() {
    }

    public Major(String name) {
        this.name = name;
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

}
