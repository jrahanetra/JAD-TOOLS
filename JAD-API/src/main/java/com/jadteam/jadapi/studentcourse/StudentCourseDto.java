package com.jadteam.jadapi.studentcourse;

import com.jadteam.jadapi.course.CourseDto;
import com.jadteam.jadapi.student.StudentDto;

/**
 * StudentCourseDto
 */
public record StudentCourseDto(StudentCourseId studentCourseId, StudentDto studentDto, CourseDto courseDto, boolean attending, boolean justificated) {
}
