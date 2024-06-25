package com.jadteam.jadapi.subject;

import java.util.List;

import com.jadteam.jadapi.course.Course;
import com.jadteam.jadapi.majorlevelsubject.MajorLevelSubject;
import com.jadteam.jadapi.teacher.Teacher;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Subject
 */
@Entity
@Table
public class Subject {
    
	@Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer hourNumber;

    @OneToMany(mappedBy = "subject")
    private List<MajorLevelSubject> majorLevelSubjects;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "subject")
    private List<Course> courseList;

    public Subject() {
    }

    public Subject(String name, Integer hourNumber) {
        this.name = name;
        this.hourNumber = hourNumber;
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

    public Integer getHourNumber() {
        return hourNumber;
    }

    public void setHourNumber(Integer hourNumber) {
        this.hourNumber = hourNumber;
    }

    public List<MajorLevelSubject> getMajorLevelSubjects() {
        return majorLevelSubjects;
    }

    public void setMajorLevelSubjects(List<MajorLevelSubject> majorLevelSubjects) {
        this.majorLevelSubjects = majorLevelSubjects;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

}
