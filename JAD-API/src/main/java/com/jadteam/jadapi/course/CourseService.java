package com.jadteam.jadapi.course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jadteam.jadapi.subject.SubjectDto;
import com.jadteam.jadapi.subject.SubjectService;

import org.springframework.stereotype.Service;

/**
 * CourseService
 */
@Service
public class CourseService {
    
	private final CourseRepository courseRepository;
    private final SubjectService subjectService;

    public CourseService(CourseRepository courseRepository, SubjectService subjectService) {
        this.courseRepository = courseRepository;
        this.subjectService = subjectService;
    }

    public CourseDto toCourseDto(Course course) {
        if (course == null)
            throw new NullPointerException("L'objet Course est invalide.");
        SubjectDto subjectDto = subjectService.toSubjectDto(course.getSubject());
        CourseDto courseDto = new CourseDto(course.getCourseId(),
                                            course.getCourseDate(),
                                            course.getCourseBeginTime(),
                                            course.getCourseEndTime(),
                                            subjectDto);
        return courseDto;
    }

    public CourseDto saveCourse(Course course) {
        if (course == null)
            throw new NullPointerException("L'object Course est invalide.");
        courseRepository.save(course);
        return toCourseDto(course);
    }

    public List<CourseDto> findAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDto> courseDtos = new ArrayList<>();
        for (var course: courses)
            courseDtos.add(toCourseDto(course));
        return courseDtos;
    }

    public CourseDto findCourseById(Integer id) {
        if (id == null)
            throw new NullPointerException("L'ID du cours fourni est invalide.");
        Course course = courseRepository.findById(id).orElse(new Course());
        CourseDto courseDto = toCourseDto(course);
        return courseDto;
    }

    public List<CourseDto> findAllCoursesByDate(LocalDate date) {
        if (date == null)
            throw new NullPointerException("The date is invalid.");
        List<Course> courses = courseRepository.findAllByCourseDate(date);
        List<CourseDto> courseDtos = new ArrayList<>();
        for (var course: courses)
            courseDtos.add(toCourseDto(course));
        return courseDtos;
    }

}
