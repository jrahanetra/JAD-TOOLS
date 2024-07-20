package com.jadteam.jadapi.teacher;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public TeacherDto toTeacherDto(Teacher teacher) {
        if (teacher == null)
            throw new NullPointerException("L'objet Teacher à convertir est invalide.");
        TeacherDto teacherDto = new TeacherDto(teacher.getTeacherId(),
                                               teacher.getFirstname(),
                                               teacher.getLastname());
        return teacherDto;
    }
    
    public List<TeacherDto> findAllTeacher() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherDto> teacherDtos = new ArrayList<>();
        for (var teacher: teachers)
            teacherDtos.add(toTeacherDto(teacher));
        return teacherDtos;
    }

    public TeacherDto findTeacherById(Integer id) {
        if (id == null)
            throw new NullPointerException("L'ID de l'objet Teacher est invalide.");
        Teacher teacher = teacherRepository.findById(id).orElse(new Teacher());
        return toTeacherDto(teacher);
    }

    public TeacherDto saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        return toTeacherDto(teacher);
    }

}
