package com.jadteam.jadapi.teacher;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    
    public List<Teacher> findAllTeacher() {
        return teacherRepository.findAll();
    }

    public Teacher findTeacherById(Integer id) {
        return teacherRepository.findById(id).orElse(null);
    }

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

}
