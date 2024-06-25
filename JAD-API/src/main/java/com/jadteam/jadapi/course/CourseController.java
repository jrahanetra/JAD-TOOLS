package com.jadteam.jadapi.course;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CourseController
 */
@RestController
@RequestMapping("/courses")
public class CourseController {
    
	private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("")
    public Course addCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @GetMapping("")
    public List<Course> findAllCourses() {
        return courseService.findAllCourses();
    }
    
    @GetMapping("/{id}")
    public Course findCourseById(@PathVariable Integer id) {
        return courseService.findCourseById(id);
    }
    
}
