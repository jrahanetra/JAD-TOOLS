package com.jadteam.jadapi.teacher;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TeacherController
 */
@RestController
@RequestMapping("/teachers")
public class TeacherController {
    
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("")
    public List<TeacherDto> findAllTeachers() {
        return teacherService.findAllTeacher();
    }

    @GetMapping("/{id}")
    public TeacherDto findTeacherById(@PathVariable("id") Integer id) {
        return teacherService.findTeacherDtoById(id);
    }

    @PostMapping("")
    public TeacherDto addTeacher(@RequestBody Teacher teacher) {
        return teacherService.saveTeacher(teacher);
    }

}
