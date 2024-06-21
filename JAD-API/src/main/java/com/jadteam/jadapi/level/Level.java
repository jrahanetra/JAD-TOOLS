package com.jadteam.jadapi.level;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jadteam.jadapi.student.Student;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "level")
public class Level {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "level")
    @JsonManagedReference
    private List<Student> studentList;

    public Level() {

    }

    public Level(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudent() {
        return this.studentList;
    }

    public void setStudent(List<Student> studentList) {
        this.studentList = studentList;
    }

}
