package com.jadteam.jadapi.registration;

import com.jadteam.jadapi.level.Level;
import com.jadteam.jadapi.major.Major;
import com.jadteam.jadapi.student.Student;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

/**
 * Registration
 */
@Entity
public class Registration {
    
	@EmbeddedId
    private RegistrationKey id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("majorId")
    @JoinColumn(name = "major_id")
    private Major major;

    @ManyToOne
    @MapsId("levelId")
    @JoinColumn(name = "level_id")
    private Level level;

    private Integer year;

    public Registration() {
    }

    public Registration(RegistrationKey id, Student student, Major major, Level level, Integer year) {
        this.id = id;
        this.student = student;
        this.major = major;
        this.level = level;
        this.year = year;
    }

    public RegistrationKey getId() {
        return id;
    }

    public void setId(RegistrationKey id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
    
}
