package com.jadteam.jadapi.subject;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "subjectId")
public class Subject {
    
	@Id
    @GeneratedValue
    private Integer subjectId;
    private String subjectName;
    private Integer hourNumber;

    @OneToMany(mappedBy = "subject")
    private List<MajorLevelSubject> majorLevelSubjects;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonBackReference
    private Teacher teacher;

    @OneToMany(mappedBy = "subject")
    private List<Course> courseList;

    public Subject() {
    }

    public Subject(String name, Integer hourNumber) {
        this.subjectName = name;
        this.hourNumber = hourNumber;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer id) {
        this.subjectId = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String name) {
        this.subjectName = name;
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
