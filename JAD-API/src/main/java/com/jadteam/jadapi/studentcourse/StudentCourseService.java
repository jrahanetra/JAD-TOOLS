package com.jadteam.jadapi.studentcourse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jadteam.jadapi.course.Course;
import com.jadteam.jadapi.course.CourseDto;
import com.jadteam.jadapi.course.CourseRepository;
import com.jadteam.jadapi.course.CourseService;
import com.jadteam.jadapi.student.Student;
import com.jadteam.jadapi.student.StudentDto;
import com.jadteam.jadapi.student.StudentRepository;
import com.jadteam.jadapi.student.StudentService;

import org.springframework.stereotype.Service;

/**
 * StudentCourseService
 */
@Service
public class StudentCourseService {
    
    private final StudentCourseRepository studentCourseRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    public StudentCourseService(StudentCourseRepository studentCourseRepository, CourseRepository courseRepository, StudentRepository studentRepository, StudentService studentService, CourseService courseService) {
        this.studentCourseRepository = studentCourseRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public StudentCourseDto toStudentCourseDto(StudentCourse studentCourse) {
        if (studentCourse == null)
            throw new NullPointerException("The Student is invalid.");
        StudentDto studentDto = studentService.toStudentDto(studentCourse.getStudent());
        CourseDto courseDto = courseService.toCourseDto(studentCourse.getCourse());
        StudentCourseDto studentCourseDto = new StudentCourseDto(studentCourse.getStudentCourseId(), studentDto,
                courseDto, studentCourse.isAttending(), studentCourse.isJustificated());
        return studentCourseDto;
    }

    public StudentCourse saveStudentCourse(Integer studentId, Integer courseId, Boolean attending, Boolean justification) {
        if (studentId == null)
            throw new NullPointerException("The Student ID is invalid.");
        if (courseId == null)
            throw new NullPointerException("The Course ID is invalid.");
        if (attending == null)
            throw new NullPointerException("The Attending is invalid.");
        if (justification == null)
            throw new NullPointerException("The justification is invalid.");
        StudentCourseId studentCourseId = new StudentCourseId(studentId, courseId);
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);
        if (student == null)
            throw new NullPointerException("Student not found");
        if (course == null)
            throw new NullPointerException("Course not found");
        StudentCourse studentCourse = new StudentCourse(studentCourseId, attending, justification);
        studentCourse.setStudent(student);
        studentCourse.setCourse(course);
        return studentCourseRepository.save(studentCourse);
    }

    public List<StudentCourseDto> findAllStudentCourses() {
        return studentCourseRepository.findAll().stream().map(s -> toStudentCourseDto(s)).toList();
    }

    public List<StudentCourseDto> findAllStudentCoursesByDate(LocalDate date) {
        if (date == null)
            throw new NullPointerException("La date fournie est invalide.");
        List<Course> courses = courseRepository.findAllByCourseDate(date);
        if (courses.isEmpty())
            return new ArrayList<>();
        List<StudentCourseDto> studentCourseDtos = studentCourseRepository.findAllByCourse(courses.get(0)).stream().map(s -> toStudentCourseDto(s)).toList();
        return studentCourseDtos;
    }

    public List<StudentCourseDto> findAllStudentCoursesByStudentId(Integer studentId) {
        if (studentId == null)
            throw new NullPointerException("The studentId is invalid.");
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null)
            throw new NullPointerException("The student you want do not exist.");
        List<StudentCourseDto> studentcourses = studentCourseRepository.findAllByStudent(student).stream()
                .map(s -> toStudentCourseDto(s)).toList();
        return studentcourses;
    }
    
}
