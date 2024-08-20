package com.jadteam.jadapi.course;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public CourseDto addCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @GetMapping("")
    public List<CourseDto> findAllCourses() {
        return courseService.findAllCourses();
    }
    
    @GetMapping("/{id}")
    public CourseDto findCourseById(@PathVariable Integer id) {
        return courseService.findCourseById(id);
    }

    @GetMapping("/d")
    public List<CourseDto> findAllCoursesByDate(@RequestParam String beginDate, @RequestParam String endDate) {
        LocalDate beginLocalDate = LocalDate.parse(beginDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate endLocalDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return courseService.findAllCoursesBetweenDates(beginLocalDate, endLocalDate);
    }
    
}
