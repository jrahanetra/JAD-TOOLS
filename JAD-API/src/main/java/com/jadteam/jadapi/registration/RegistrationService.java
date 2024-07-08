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
            throw new NullPointerException("L'objet inscription est null.");
        StudentDto studentDto = studentService.toStudentDto(registration.getStudent());
        LevelDto levelDto = levelService.toLevelDto(registration.getLevel());
        MajorDto majorDto = majorService.toMajorDto(registration.getMajor());
        RegistrationDto registrationDto = new RegistrationDto(studentDto, majorDto, levelDto, registration.getYear());
        return registrationDto;
    }

    public RegistrationDto saveRegistration(Integer studentId, Integer majorId, Integer levelId, Integer year) {
        if (studentId == null || levelId == null || majorId == null)
            throw new NullPointerException("No parameter should be null.");
        Student student = studentService.findStudentbyId(studentId);
        Major major = majorService.findMajorById(majorId);
        Level level = levelService.findLevelById(levelId);
        if (student == null || major == null || level == null)
            throw new NullPointerException("L'un des informations recherchées n'est pas dans la base de données.");
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
        Major major = majorService.findMajorById(majorId);
        Level level = levelService.findLevelById(levelId);
        if (major == null || level == null)
            throw new NullPointerException("L'un des informations fournies est incorrecte.");
        List<Registration> registrations = registrationRepository.findAllByMajorAndLevel(major, level);
        List<RegistrationDto> registrationDtos = new ArrayList<>();
        for (var registration: registrations)
            registrationDtos.add(toRegistrationDto(registration));
        return registrationDtos;
    }

    public List<RegistrationDto> findAllRegistrationsByMajor(Integer majorId, Integer year) {
        if (majorId == null || year == null)
            throw new NullPointerException("Une ou plusieurs information(s) fournie(s) est/sont nulle(s).");
        Major major = majorService.findMajorById(majorId);
        if (major == null)
            throw new NullPointerException("Il n'y a aucun parcours avec cet ID.");
        List<Registration> registrations = registrationRepository.findAllByMajor(major);
        List<Registration> filteredRegistrations = registrations.stream()
            .filter(reg -> reg.getYear().equals(year))
            .toList();
        List<RegistrationDto> registrationDtos = new ArrayList<>();
        for (var registration: filteredRegistrations)
            registrationDtos.add(toRegistrationDto(registration));
        return registrationDtos;
    }

    public List<RegistrationDto> findAllRegistrationsByLevel(Integer levelId, Integer year) {
        if (levelId == null || year == null)
            throw new NullPointerException("Une ou plusieurs information(s) fournie(s) est/sont nulle(s)");
        Level level = levelService.findLevelById(levelId);
        if (level == null)
            throw new NullPointerException("Il n'y a aucun niveau avec cet ID.");
        List<Registration> registrations = registrationRepository.findAllByLevel(level);
        List<Registration> filteredRegistrations = registrations.stream()
            .filter(reg -> reg.getYear().equals(year))
            .toList();
        List<RegistrationDto> registrationDtos = new ArrayList<>();
        for (var registration: filteredRegistrations)
            registrationDtos.add(toRegistrationDto(registration));
        return registrationDtos;
    }

}
