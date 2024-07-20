package com.jadteam.jadapi.student;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private static List<Student> students = new ArrayList<>();

    static {
        Student student1 = new Student("Antsa", "Rafanomezantsoa", "Ampitatafika", "antsa@email.com", "032 71 720 97");
        Student student2 = new Student("Jason", "Rahanetra", "Ambatoroka", "jason@email.com", "038 77 667 97");
        Student student3 = new Student("Dihary", "Rabearimanana", "Andranomena", "dihary@email.com", "034 09 241 65");
        students.add(student1);
        students.add(student2);
        students.add(student3);
    }

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;

        for (var student: students)
            this.studentRepository.save(student);
    }

    public StudentDto toStudentDto(Student student) {
        if (student == null)
            throw new NullPointerException("Les informations de l'étudiant sont invalides.");
        StudentDto studentDto = new StudentDto(student.getStudentId(), student.getFirstname(), student.getLastname(),
                student.getAddress(), student.getEmail(), student.getPhoneNumber());
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
