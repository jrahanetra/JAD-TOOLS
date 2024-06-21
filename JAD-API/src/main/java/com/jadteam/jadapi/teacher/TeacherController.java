package com.jadteam.jadapi.teacher;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<Teacher> findAllLevel() {
        return teacherService.findAllTeacher();
    }

    @GetMapping("/{id}")
    public Teacher findLevelById(@PathVariable("id") Integer id) {
        return teacherService.findTeacherById(id);
    }

}
