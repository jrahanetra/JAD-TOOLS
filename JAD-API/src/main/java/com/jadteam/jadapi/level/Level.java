package com.jadteam.jadapi.level;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Level {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    
    public Level() {
    }

    public Level(Integer id, String name) {
        this.id = id;
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
