package com.jadteam.jadapi.course;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CourseRepository
 */
public interface CourseRepository extends JpaRepository<Course, Integer> {

    public List<Course> findAllByCourseDate(LocalDate courseDate);

}
