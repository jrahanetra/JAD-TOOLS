package com.jadteam.jadapi.teacher;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private static List<Teacher> teachers = new ArrayList<>();

    static {
        Teacher t1 = new Teacher("Tsinto", "Ratiarison");
        Teacher t2 = new Teacher("Paulson", "Ravomampiandra");
        Teacher t3 = new Teacher("Aina", "Lucas");
        Teacher t4 = new Teacher("Houlder", "Rakotoson");
        Teacher t5 = new Teacher("Tolotra", "Andrianalimanana");
        teachers.add(t1);
        teachers.add(t2);
        teachers.add(t3);
        teachers.add(t4);
        teachers.add(t5);
    }

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;

        for (var teacher: teachers)
            this.teacherRepository.save(teacher);
    }

    public TeacherDto toTeacherDto(Teacher teacher) {
        if (teacher == null)
            throw new NullPointerException("The Teacher is invalid.");
        TeacherDto teacherDto = new TeacherDto(teacher.getTeacherId(),
                                               teacher.getFirstname(),
                                               teacher.getLastname());
        return teacherDto;
    }

    public Teacher toTeacher(TeacherDto teacherDto) {
        if (teacherDto == null)
            throw new NullPointerException("The TeacherDto is invalid.");
        Teacher teacher = new Teacher(teacherDto.firstname(),
                                      teacherDto.lastname());
        teacher.setTeacherId(teacherDto.teacherId());
        return teacher;
    }
    
    public List<TeacherDto> findAllTeacher() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherDto> teacherDtos = new ArrayList<>();
        for (var teacher: teachers)
            teacherDtos.add(toTeacherDto(teacher));
        return teacherDtos;
    }

    public TeacherDto findTeacherDtoById(Integer id) {
        if (id == null)
            throw new NullPointerException("L'ID de l'objet Teacher est invalide.");
        Teacher teacher = teacherRepository.findById(id).orElse(new Teacher());
        return toTeacherDto(teacher);
    }

    public Teacher findTeacherById(Integer id) {
        return teacherRepository.findById(id).orElse(new Teacher());
    }

    public TeacherDto saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        return toTeacherDto(teacher);
    }

}
