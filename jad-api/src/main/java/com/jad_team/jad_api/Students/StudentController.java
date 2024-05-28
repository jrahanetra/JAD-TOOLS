package com.jad.jadapi.Students;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * StudentController
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository repository;

	public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "")
    public Student registerStudent(@RequestBody Student student) {
        return repository.save(student);
    }

    @GetMapping(value = "")
    public List<Student> findAllStudents() {
        return repository.findAll();
    }

    @GetMapping(value = "{id}")
    public Student findStudentbyId(@PathVariable("id") Integer id) {
        return repository.findById(id)
            .orElse(new Student());
    }
    
    @DeleteMapping(value = "{id}")
    public void deleteStudentById(@PathVariable("id") Integer id) {
        repository.deleteById(id);
    }
}
