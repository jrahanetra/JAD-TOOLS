package com.jadteam.jadapi.course;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * CourseService
 */
@Service
public class CourseService {
    
	private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    public Course findCourseById(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }

}
