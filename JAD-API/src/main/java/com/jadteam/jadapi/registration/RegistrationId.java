package com.jadteam.jadapi.registration;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * RegistrationKey
 */
@Embeddable
public class RegistrationId implements Serializable {
    
	@Column(name = "student_id")
    private Integer studentId;
    @Column(name = "major_id")
    private Integer majorId;
    @Column(name = "level_id")
    private Integer levelId;

    public RegistrationId() {
    }

    public RegistrationId(Integer studentId, Integer majorId, Integer levelId) {
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

}
