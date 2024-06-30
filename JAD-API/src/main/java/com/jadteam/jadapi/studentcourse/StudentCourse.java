package com.jadteam.jadapi.studentcourse;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.jadteam.jadapi.course.Course;
import com.jadteam.jadapi.student.Student;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

/**
 * StudentCourse
 */
@Entity
@Table
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "studentCourseId")
public class StudentCourse {
    
	@EmbeddedId
    private StudentCourseId studentCourseId;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    private boolean attending;
    private boolean justificated;

    public StudentCourse() {
    }

    public StudentCourse(StudentCourseId studentCourseId, boolean attending, boolean justification) {
        this.studentCourseId = studentCourseId;
        this.attending = attending;
        this.justificated = justification;
    }

    public StudentCourseId getStudentCourseId() {
        return studentCourseId;
    }

    public void setStudentCourseId(StudentCourseId studentCourseId) {
        this.studentCourseId = studentCourseId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public boolean isAttending() {
        return attending;
    }

    public void setAttending(boolean attending) {
        this.attending = attending;
    }

    public boolean isJustificated() {
        return justificated;
    }

    public void setJustificated(boolean justificated) {
        this.justificated = justificated;
    }

}
