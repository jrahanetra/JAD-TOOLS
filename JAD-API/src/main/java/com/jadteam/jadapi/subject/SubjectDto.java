package com.jadteam.jadapi.subject;

import com.jadteam.jadapi.teacher.TeacherDto;

/**
 * SubjectDto
 */
public record SubjectDto(Integer subjectId, String subjectName,
                         Integer hourNumber, TeacherDto teacherDto) {
}
