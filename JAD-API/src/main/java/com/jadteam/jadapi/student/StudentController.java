package com.jadteam.jadapi.student;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;


/**
 * StudentController
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

	public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("")
    public StudentDto registerStudent(@RequestBody Student student) throws Exception {
        return studentService.saveStudent(student);
    }

    @GetMapping("")
    public List<StudentDto> findAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/{id}")
    public Student findStudentbyId(@PathVariable("id") Integer id) {
        return studentService.findStudentbyId(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable("id") Integer id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }
    
    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable("id") Integer id) {
        studentService.deleteStudentById(id);;
    }
}
