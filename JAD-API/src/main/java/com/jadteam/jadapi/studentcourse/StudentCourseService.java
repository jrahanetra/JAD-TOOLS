package com.jadteam.jadapi.studentcourse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jadteam.jadapi.course.Course;
import com.jadteam.jadapi.course.CourseDto;
import com.jadteam.jadapi.course.CourseRepository;
import com.jadteam.jadapi.course.CourseService;
import com.jadteam.jadapi.level.Level;
import com.jadteam.jadapi.level.LevelRepository;
import com.jadteam.jadapi.major.Major;
import com.jadteam.jadapi.majorlevelsubject.MajorLevelSubject;
import com.jadteam.jadapi.majorlevelsubject.MajorLevelSubjectRepository;
import com.jadteam.jadapi.registration.Registration;
import com.jadteam.jadapi.student.Student;
import com.jadteam.jadapi.student.StudentDto;
import com.jadteam.jadapi.student.StudentRepository;
import com.jadteam.jadapi.student.StudentService;
import com.jadteam.jadapi.subject.Subject;

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
    private final MajorLevelSubjectRepository majorLevelSubjectRepository;
    private final LevelRepository levelRepository;

    public StudentCourseService(StudentCourseRepository studentCourseRepository, CourseRepository courseRepository,
            StudentRepository studentRepository, StudentService studentService, CourseService courseService,
            MajorLevelSubjectRepository majorLevelSubjectRepository, LevelRepository levelRepository) {
        this.studentCourseRepository = studentCourseRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.studentService = studentService;
        this.courseService = courseService;
        this.majorLevelSubjectRepository = majorLevelSubjectRepository;
        this.levelRepository = levelRepository;
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

    public StudentCourse saveStudentCourse(Integer studentId, Integer courseId, Boolean attending, Boolean justification)
    throws Exception {
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
        Registration studentCurrentRegistration = student.getRegistrations()
            .stream()
            .filter(s -> s.getYear() == LocalDate.now().getYear())
            .toList()
            .get(0);
        Subject courseSubject = course.getSubject();
        List<MajorLevelSubject> courseMajorLevelSubjects = majorLevelSubjectRepository.findAllBySubject(courseSubject);
        List<Major> courseMajors = courseMajorLevelSubjects.stream().map(m -> m.getMajor()).toList();
        List<Level> courseLevels = courseMajorLevelSubjects.stream().map(m -> m.getLevel()).toList();
        if (!courseMajors.contains(studentCurrentRegistration.getMajor()) ||
            !courseLevels.contains(studentCurrentRegistration.getLevel()))
            throw new Exception("The student is not subscribed to this course.");
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
            throw new NullPointerException("The date is invalid.");
        List<Course> courses = courseRepository.findAllByCourseDate(date);
        if (courses.isEmpty())
            return new ArrayList<>();
        List<StudentCourseDto> studentCourseDtos = studentCourseRepository.findAllByCourse(courses.get(0)).stream().map(s -> toStudentCourseDto(s)).toList();
        return studentCourseDtos;
    }

    public List<StudentCourseDto> findAllStudentCoursesByCourseId(Integer courseId) {
        if (courseId == null)
            throw new NullPointerException("The Student ID is invalid.");
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null)
            throw new NullPointerException("Student not found.");
        List<StudentCourseDto> studentcourses = studentCourseRepository.findAllByCourse(course).stream()
                .map(s -> toStudentCourseDto(s)).toList();
        return studentcourses;
    }

    public List<StudentCourseDto> findAllStudentCoursesByStudentId(Integer studentId) {
        if (studentId == null)
            throw new NullPointerException("The Student ID is invalid.");
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null)
            throw new NullPointerException("Student not found.");
        List<StudentCourseDto> studentcourses = studentCourseRepository.findAllByStudent(student).stream()
                .map(s -> toStudentCourseDto(s)).toList();
        return studentcourses;
    }

    public List<StudentCourseDto> findAllStudentCoursesByLevelId(Integer levelId) {
        if (levelId == null)
            throw new NullPointerException("The Level ID is invalid.");
        Level level = levelRepository.findById(levelId).orElse(null);
        if (level == null)
            throw new NullPointerException("Level not found.");
        List<Subject> subjects = majorLevelSubjectRepository.findAllByLevel(level).stream().map(m -> m.getSubject()).toList();
        List<Course> courses = new ArrayList<>();
        for (var subject: subjects)
            courses.addAll(courseRepository.findAllBySubject(subject));
        List<StudentCourseDto> scd = new ArrayList<>();
        for (var course: courses)
            scd.addAll(studentCourseRepository.findAllByCourse(course).stream().map(sc -> toStudentCourseDto(sc)).toList());
        return scd;
    }
    
}
