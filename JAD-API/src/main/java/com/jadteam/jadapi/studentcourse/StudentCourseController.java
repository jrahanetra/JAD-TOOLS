package com.jadteam.jadapi.studentcourse;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * StudentCourseController
 */
@RestController
@RequestMapping("/studentcourses")
public class StudentCourseController {
    
    private final StudentCourseService studentCourseService;

    public StudentCourseController(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }

    @PostMapping("")
    public StudentCourse addStudentCourse(@RequestBody StudentCourse studentCourse) {
        return studentCourseService.saveStudentCourse(studentCourse);
    }

    @GetMapping("")
    public List<StudentCourse> findAllStudentCourses() {
        return studentCourseService.findAllStudentCourses();
    }

    @GetMapping("/d")
    public List<StudentCourse> findAllStudentCoursesByDate(@RequestBody LocalDate date) {
        return studentCourseService.findAllStudentCoursesByDate(date);
    }
    
}
