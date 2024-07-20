package com.jadteam.jadapi.studentcourse;

import com.jadteam.jadapi.course.Course;
import com.jadteam.jadapi.student.StudentDto;

/**
 * StudentCourseDto
 */
public record StudentCourseDto(StudentCourseId studentCourseId, StudentDto studentDto, Course course, boolean attending, boolean justificated) {
}
