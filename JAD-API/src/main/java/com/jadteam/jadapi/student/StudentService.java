package com.jadteam.jadapi.student;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private static List<Student> studentList = new ArrayList<>();
    
    static {
        Student s1 = new Student("Antsa", "Rafanomezantsoa", "Ambohimamory", "antsa@email.com", "034 71 720 97");
        Student s2 = new Student("Jason", "Rahanetra", "Ambatoroka", "jason@email.com", "038 77 667 97");
        Student s3 = new Student("Dihariniaina", "Rabearimanana", "Andranomena", "dihary@mail.com", "034 09 241 65");
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);
    }

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        for (var student: studentList)
            this.studentRepository.save(student);
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
