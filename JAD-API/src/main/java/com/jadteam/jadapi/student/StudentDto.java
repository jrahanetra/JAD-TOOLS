package com.jadteam.jadapi.student;

import java.time.LocalDate;

/**
 * StudentDto
 */
public record StudentDto(Integer studentId, String firstname, String lastname, String address, String email, String phoneNumber, Sex sex, LocalDate birthday, String imageName) {
}
