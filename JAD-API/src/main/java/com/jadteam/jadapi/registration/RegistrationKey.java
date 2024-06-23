package com.jadteam.jadapi.registration;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * RegistrationKey
 */
@Embeddable
public class RegistrationKey implements Serializable {
    
	@Column(name = "student_id")
    private Integer studentId;
    @Column(name = "major_id")
    private Integer majorId;
    @Column(name = "level_id")
    private Integer levelId;

    public RegistrationKey() {
    }

    public RegistrationKey(Integer studentId, Integer majorId, Integer levelId) {
        this.studentId = studentId;
        this.majorId = majorId;
        this.levelId = levelId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
