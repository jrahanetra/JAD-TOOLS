package com.jadteam.jadapi.course;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.jadteam.jadapi.studentcourse.StudentCourse;
import com.jadteam.jadapi.subject.Subject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Course
 */
@Entity
@Table
public class Course {
    
	@Id
    @GeneratedValue
    private Integer courseId;
    private LocalDate courseDate;
    private LocalTime courseBeginTime;
    private LocalTime courseEndTime;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "course")
    private List<StudentCourse> studentCourseList;

    public Course() {
    }

    public Course(LocalDate courseDate, LocalTime courseBeginTime, LocalTime courseEndTime) {
        this.courseDate = courseDate;
        this.courseBeginTime = courseBeginTime;
        this.courseEndTime = courseEndTime;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public LocalTime getCourseEndTime() {
        return courseEndTime;
    }

    public void setCourseEndTime(LocalTime courseEndTime) {
        this.courseEndTime = courseEndTime;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<StudentCourse> getStudentCourseList() {
        return studentCourseList;
    }

    public void setStudentCourseList(List<StudentCourse> studentCourseList) {
        this.studentCourseList = studentCourseList;
    }

    public LocalDate getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(LocalDate courseDate) {
        this.courseDate = courseDate;
    }

    public LocalTime getCourseBeginTime() {
        return courseBeginTime;
    }

    public void setCourseBeginTime(LocalTime courseBeginTime) {
        this.courseBeginTime = courseBeginTime;
    }

}
