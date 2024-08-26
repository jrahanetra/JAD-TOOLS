package com.jadteam.jadapi.student;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentDto toStudentDto(Student student) {
        if (student == null)
            throw new NullPointerException("The Student to convert is invalid.");
        StudentDto studentDto = new StudentDto(student.getStudentId(), student.getFirstname(), student.getLastname(),
                                               student.getAddress(), student.getEmail(), student.getPhoneNumber(), student.getSex(), student.getBirthday(), student.getImageName());
        return studentDto;
    }

    public StudentDto saveStudent(Student student) throws Exception {
        if (student == null) 
            throw new NullPointerException("The Student is invalid.");
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(student.getEmail());
        if (!matcher.matches())
            throw new Exception("Email is invalid.");
        studentRepository.save(student);
        return toStudentDto(student);
    }

    public List<StudentDto> findAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = new ArrayList<>();
        for (var student : students) {
            studentDtos.add(toStudentDto(student));
        }
        return studentDtos;
    }

    public Student findStudentById(Integer id) {
        if (id == null)
            throw new NullPointerException("The Student ID is invalid.");
        return studentRepository.findById(id)
            .orElse(new Student());
    }

    public Student updateStudent(Integer id, Student student) {
        if (id == null)
            throw new NullPointerException("The Student ID is invalid.");
        if (student == null)
            throw new NullPointerException("The Student is invalid.");
        if (findStudentById(id).equals(new Student()))
            throw new NullPointerException("Student not found.");
        studentRepository.updateStudent(id,
                                        student.getFirstname(),
                                        student.getLastname(),
                                        student.getAddress(),
                                        student.getEmail(),
                                        student.getPhoneNumber(),
                                        student.getSex(),
                                        student.getBirthday(),
                                        student.getImageName());
        return new Student();
    }

    public void deleteStudentById(Integer id) {
        if (id == null)
            throw new NullPointerException("The Student ID is invalid.");
        if (findStudentById(id).equals(new Student()))
            throw new NullPointerException("Student not found.");
        studentRepository.deleteById(id);
    }

}
