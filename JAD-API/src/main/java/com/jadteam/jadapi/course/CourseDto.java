package com.jadteam.jadapi.course;

import java.time.LocalDate;
import java.time.LocalTime;

import com.jadteam.jadapi.subject.SubjectDto;

/**
 * CourseDto
 */
public record CourseDto(Integer courseId, LocalDate courseDate,
                        LocalTime courseBeginTime, LocalTime courseEndTime,
                        SubjectDto subjectDto) {
}
