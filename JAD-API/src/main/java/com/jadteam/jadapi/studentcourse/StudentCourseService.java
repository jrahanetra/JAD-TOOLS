package com.jadteam.jadapi.studentcourse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jadteam.jadapi.course.Course;
import com.jadteam.jadapi.course.CourseRepository;

import org.springframework.stereotype.Service;

/**
 * StudentCourseService
 */
@Service
public class StudentCourseService {
    
    private final StudentCourseRepository studentCourseRepository;
    private final CourseRepository courseRepository;

    public StudentCourseService(StudentCourseRepository studentCourseRepository, CourseRepository courseRepository) {
        this.studentCourseRepository = studentCourseRepository;
        this.courseRepository = courseRepository;
    }

    public StudentCourse saveStudentCourse(StudentCourse studentCourse) {
        if (studentCourse == null)
            throw new NullPointerException("L'objet StudentCourse fourni est invalide.");
        return studentCourseRepository.save(studentCourse);
    }

    public List<StudentCourse> findAllStudentCourses() {
        return studentCourseRepository.findAll();
    }

    public List<StudentCourse> findAllStudentCoursesByDate(LocalDate date) {
        if (date == null)
            throw new NullPointerException("La date fournie est invalide.");
        List<Course> courses = courseRepository.findAllByCourseDate(date);
        if (courses.isEmpty())
            return new ArrayList<>();
        return studentCourseRepository.findAllByCourse(courses.get(0));
    }
    
}
