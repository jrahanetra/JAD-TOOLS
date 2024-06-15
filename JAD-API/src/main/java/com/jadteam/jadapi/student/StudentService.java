package com.jadteam.jadapi.student;

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

    public Student saveStudent(Student student) throws Exception {
        if (student.equals(null)) 
            throw new NullPointerException("The student should not be null.");
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(student.getEmail());
        if (!matcher.matches())
            throw new Exception("Invalid email.");
        return studentRepository.save(student);
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student findStudentbyId(Integer id) {
        return studentRepository.findById(id)
        .orElse(new Student());
    }

    public Student updateStudent(Integer id, Student student) {
        if (findStudentbyId(id).equals(new Student()))
            throw new NullPointerException("The student do not exist or has been deleted.");
        studentRepository.updateStudent(id,
                                        student.getFirstname(),
                                        student.getLastname(),
                                        student.getAddress(),
                                        student.getEmail(),
                                        student.getPhoneNumber());
        return new Student();
    }

    public void deleteStudentById(Integer id) {
        if (findStudentbyId(id).equals(new Student()))
            throw new NullPointerException("The student do not exist or has already been deleted.");
        studentRepository.deleteById(id);
    }

}
