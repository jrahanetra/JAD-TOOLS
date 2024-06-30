package com.jadteam.jadapi.registration;

import com.jadteam.jadapi.level.LevelDto;
import com.jadteam.jadapi.major.MajorDto;
import com.jadteam.jadapi.student.StudentDto;

/**
 * RegistrationDto
 */
public record RegistrationDto(StudentDto studentDto, MajorDto majorDto, LevelDto levelDto, Integer year) {
}
