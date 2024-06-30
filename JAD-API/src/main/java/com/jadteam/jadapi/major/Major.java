package com.jadteam.jadapi.major;

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

/**
 * major
 */
@Entity
@Table
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "majorId")
public class Major {
    
	@Id
    @GeneratedValue
    private Integer majorId;
    private String majorName;

    @OneToMany(mappedBy = "major")
    private List<Registration> registrationList;

    @OneToMany(mappedBy = "major")
    private List<MajorLevelSubject> majorLevelSubjectList;

    public Major() {
    }

    public Major(String majorName, List<Registration> registrationList, List<MajorLevelSubject> majorLevelSubjectList) {
        this.majorName = majorName;
        this.registrationList = registrationList;
        this.majorLevelSubjectList = majorLevelSubjectList;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer id) {
        this.majorId = id;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String name) {
        this.majorName = name;
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
