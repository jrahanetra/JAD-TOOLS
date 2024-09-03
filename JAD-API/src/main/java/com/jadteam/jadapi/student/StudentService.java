package com.jadteam.jadapi.student;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jadteam.jadapi.course.Course;
import com.jadteam.jadapi.course.CourseRepository;
import com.jadteam.jadapi.majorlevelsubject.MajorLevelSubject;
import com.jadteam.jadapi.majorlevelsubject.MajorLevelSubjectRepository;
import com.jadteam.jadapi.registration.Registration;
import com.jadteam.jadapi.registration.RegistrationRepository;
import com.jadteam.jadapi.subject.Subject;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final MajorLevelSubjectRepository majorLevelSubjectRepository;
    private final RegistrationRepository registrationRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository,
            MajorLevelSubjectRepository majorLevelSubjectRepository, RegistrationRepository registrationRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.majorLevelSubjectRepository = majorLevelSubjectRepository;
        this.registrationRepository = registrationRepository;
    }

    public StudentDto toStudentDto(Student student) {
        if (student == null)
            throw new NullPointerException("The Student to convert is invalid.");
        StudentDto studentDto = new StudentDto(student.getStudentId(), student.getFirstname(), student.getLastname(),
                student.getAddress(), student.getEmail(), student.getPhoneNumber(), student.getSex(),
                student.getBirthday(), student.getImageName());
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

    public List<StudentDto> findAllStudentsByCourseId(Integer courseId) {
        if (courseId == null)
            throw new NullPointerException("The Course ID is invalid.");
        Subject subject = courseRepository.findById(courseId).orElse(new Course()).getSubject();
        System.out.println(subject.getSubjectName());
        List<MajorLevelSubject> mls = majorLevelSubjectRepository.findAllBySubject(subject);
        List<Registration> registrations = new ArrayList<>();
        for (var m : mls) {
            registrations
                    .addAll(registrationRepository
                            .findAllByMajorAndLevel(m.getMajor(), m.getLevel()));
        }
        return registrations.stream().map(r -> toStudentDto(r.getStudent())).toList();
    }

    public Student updateStudent(Integer id, Student student) {
        if (id == null)
            throw new NullPointerException("The Student ID is invalid.");
        if (student == null)
            throw new NullPointerException("The Student is invalid.");
        if (findStudentById(id).equals(new Student()))
            throw new NullPointerException("Student not found");
        studentRepository.updateStudent(
                id,
                student.getFirstname(),
                student.getLastname(),
                student.getAddress(),
                student.getEmail(),
                student.getPhoneNumber(),
                student.getSex(),
                student.getBirthday(),
                student.getImageName());
        return student;
    }

    public void deleteStudentById(Integer id) {
        if (id == null)
            throw new NullPointerException("The Student ID is invalid.");
        if (findStudentById(id).equals(new Student()))
            throw new NullPointerException("Student not found.");
        studentRepository.deleteById(id);
    }

}
