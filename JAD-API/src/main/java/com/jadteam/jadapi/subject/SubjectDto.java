package com.jadteam.jadapi.subject;

import com.jadteam.jadapi.teacher.Teacher;

/**
 * SubjectDto
 */
public record SubjectDto(Integer subjectId,
                         String subjectName,
                         Integer hourNumber,
                         Teacher teacher) {
}
