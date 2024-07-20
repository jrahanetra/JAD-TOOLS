package com.jadteam.jadapi.studentcourse;

import java.util.List;

import com.jadteam.jadapi.course.Course;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * StudentCourseRepository
 */
public interface StudentCourseRepository extends JpaRepository<StudentCourse, StudentCourseId> {

    public List<StudentCourse> findAllByCourse(Course course);
	
}
