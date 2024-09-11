package com.jadteam.jadapi.registration;

import java.util.ArrayList;
import java.util.List;

import com.jadteam.jadapi.level.Level;
import com.jadteam.jadapi.level.LevelDto;
import com.jadteam.jadapi.level.LevelService;
import com.jadteam.jadapi.major.Major;
import com.jadteam.jadapi.major.MajorDto;
import com.jadteam.jadapi.major.MajorService;
import com.jadteam.jadapi.student.Student;
import com.jadteam.jadapi.student.StudentDto;
import com.jadteam.jadapi.student.StudentService;

import org.springframework.stereotype.Service;

/**
 * RegistrationService
 */
@Service
public class RegistrationService {
    
    private final RegistrationRepository registrationRepository;
    private final StudentService studentService;
    private final LevelService levelService;
    private final MajorService majorService;
    
    public RegistrationService(RegistrationRepository registrationRepository, StudentService studentService,
            LevelService levelService, MajorService majorService) {
        this.registrationRepository = registrationRepository;
        this.studentService = studentService;
        this.levelService = levelService;
        this.majorService = majorService;
    }

    public RegistrationDto toRegistrationDto(Registration registration) {
        if (registration == null)
            throw new NullPointerException("The Registration to convert is invalid.");
        StudentDto studentDto = studentService.toStudentDto(registration.getStudent());
        LevelDto levelDto = levelService.toLevelDto(registration.getLevel());
        MajorDto majorDto = majorService.toMajorDto(registration.getMajor());
        RegistrationDto registrationDto = new RegistrationDto(studentDto, majorDto, levelDto, registration.getYear());
        return registrationDto;
    }

    public RegistrationDto saveRegistration(Integer studentId, Integer majorId, Integer levelId, Integer year) {
        if (studentId == null)
            throw new NullPointerException("The Student ID is invalid.");
        if (majorId == null)
            throw new NullPointerException("The Major ID is invalid.");
        if (levelId == null)
            throw new NullPointerException("The Level ID is invalid.");
        if (year == null)
            throw new NullPointerException("The year is invalid.");
        Student student = studentService.findStudentById(studentId);
        Major major = majorService.findMajorById(majorId);
        Level level = levelService.findLevelById(levelId);
        if (student == null || major == null || level == null)
            throw new NullPointerException("One of the information required could not be found.");
        RegistrationId id = new RegistrationId(studentId, majorId, levelId);
        Registration reg = new Registration(id, student, major, level, year);
        RegistrationDto regDto = toRegistrationDto(reg);
        registrationRepository.save(reg);
        return regDto;
    }

    public List<RegistrationDto> findAllRegistrations() {
        List<Registration> registrations = registrationRepository.findAll();
        List<RegistrationDto> registrationDtos = new ArrayList<>();
        for (var registration: registrations)
            registrationDtos.add(toRegistrationDto(registration));
        return registrationDtos;
    }

    public List<RegistrationDto> findAllRegistrationsByMajorAndLevel(Integer majorId, Integer levelId) {
        if (majorId == null)
            throw new NullPointerException("The Major ID is invalid.");
        if (levelId == null)
            throw new NullPointerException("The Level ID is invalid.");
        Major major = majorService.findMajorById(majorId);
        Level level = levelService.findLevelById(levelId);
        if (major == null || level == null)
            return new ArrayList<>();
        List<Registration> registrations = registrationRepository.findAllByMajorAndLevel(major, level);
        List<RegistrationDto> registrationDtos = new ArrayList<>();
        for (var registration: registrations)
            registrationDtos.add(toRegistrationDto(registration));
        return registrationDtos;
    }
}
