package com.jadteam.jadapi.level;
import java.util.List;

import com.jadteam.jadapi.majorlevelsubject.MajorLevelSubject;
import com.jadteam.jadapi.registration.Registration;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Level {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "level")
    private List<Registration> registrationList;

    @OneToMany(mappedBy = "level")
    private List<MajorLevelSubject> majorLevelSubjectList;

    public Level() {

    }

    public Level(String name) {
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

    public List<Registration> getRegistrationList() {
        return registrationList;
    }

    public void setRegistrationList(List<Registration> registration) {
        this.registrationList = registration;
    }

    public List<MajorLevelSubject> getMajorLevelSubjectList() {
        return majorLevelSubjectList;
    }

    public void setMajorLevelSubjectList(List<MajorLevelSubject> majorLevelSubjects) {
        this.majorLevelSubjectList = majorLevelSubjects;
    }

}
