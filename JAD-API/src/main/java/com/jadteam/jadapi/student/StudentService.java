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
            throw new NullPointerException("Les informations de l'étudiant sont invalides.");
        StudentDto studentDto = new StudentDto(student.getStudentId(), student.getFirstname(), student.getLastname(),
                                               student.getAddress(), student.getEmail(), student.getPhoneNumber(), student.getSex(), student.getBirthday(), student.getImageName());
        return studentDto;
    }

    public StudentDto saveStudent(Student student) throws Exception {
        if (student == null) 
            throw new NullPointerException("The student should not be null.");
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(student.getEmail());
        if (!matcher.matches())
            throw new Exception("Invalid email.");
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
        return studentRepository.findById(id)
        .orElse(new Student());
    }

    public Student updateStudent(Integer id, Student student) {
        if (findStudentById(id).equals(new Student()))
            throw new NullPointerException("The student do not exist or has been deleted.");
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
        if (findStudentById(id).equals(new Student()))
            throw new NullPointerException("The student do not exist or has already been deleted.");
        studentRepository.deleteById(id);
    }

}
