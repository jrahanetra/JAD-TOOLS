package com.jadteam.jadapi.level;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.jadteam.jadapi.majorlevelsubject.MajorLevelSubject;
import com.jadteam.jadapi.registration.Registration;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "levelId")
public class Level {

    @Id
    @GeneratedValue
    private Integer levelId;
    private String levelName;

    @OneToMany(mappedBy = "level")
    private List<Registration> registrationList;

    @OneToMany(mappedBy = "level")
    private List<MajorLevelSubject> majorLevelSubjectList;

    public Level() {
    }

    public Level(String levelName, List<Registration> registrationList, List<MajorLevelSubject> majorLevelSubjectList) {
        this.levelName = levelName;
        this.registrationList = registrationList;
        this.majorLevelSubjectList = majorLevelSubjectList;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public List<Registration> getRegistrationList() {
        return registrationList;
    }

    public void setRegistrationList(List<Registration> registrationList) {
        this.registrationList = registrationList;
    }

    public List<MajorLevelSubject> getMajorLevelSubjectList() {
        return majorLevelSubjectList;
    }

    public void setMajorLevelSubjectList(List<MajorLevelSubject> majorLevelSubjects) {
        this.majorLevelSubjectList = majorLevelSubjects;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

}
