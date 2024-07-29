package com.jadteam.jadapi.subject;

import java.util.ArrayList;
import java.util.List;

import com.jadteam.jadapi.teacher.Teacher;
import com.jadteam.jadapi.teacher.TeacherDto;
import com.jadteam.jadapi.teacher.TeacherService;

import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final TeacherService teacherService;
    private static List<Subject> subjects = new ArrayList<>();

    public SubjectService(SubjectRepository subjectRepository, TeacherService teacherService) {
        this.subjectRepository = subjectRepository;
        this.teacherService = teacherService;

        Subject s1 = new Subject("Mathématiques", 60);
        Subject s2 = new Subject("Réseau", 60);
        Subject s3 = new Subject("Recherches opérationnelles", 30);
        Subject s4 = new Subject("C#", 50);
        Subject s5 = new Subject("Algorithmique et stucture des données", 50);
        Teacher t1 = this.teacherService.findTeacherById(1);
        Teacher t2 = this.teacherService.findTeacherById(2);
        Teacher t3 = this.teacherService.findTeacherById(3);
        Teacher t4 = this.teacherService.findTeacherById(4);
        Teacher t5 = this.teacherService.findTeacherById(5);
        s1.setTeacher(t1);
        s2.setTeacher(t2);
        s3.setTeacher(t3);
        s4.setTeacher(t4);
        s5.setTeacher(t5);
        subjects.add(s1);
        subjects.add(s2);
        subjects.add(s3);
        subjects.add(s4);
        subjects.add(s5);

        for (var subject: subjects)
            this.subjectRepository.save(subject);
    }

    public SubjectDto toSubjectDto(Subject subject) {
        if (subject == null)
            throw new NullPointerException("L'objet subject est null.");
        TeacherDto teacherDto = teacherService.toTeacherDto(subject.getTeacher());
        SubjectDto subjectDto = new SubjectDto(subject.getSubjectId(), subject.getSubjectName(), subject.getHourNumber(), teacherDto);
        return subjectDto;
    }

    public SubjectDto saveSubject(Subject subject) {
        if (subject == null)
            throw new NullPointerException("L'objet subject est null.");
        subjectRepository.save(subject);
        return toSubjectDto(subject);
    }

    public List<SubjectDto> findAllSubject() {
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectDto> subjectDtos = new ArrayList<>();
        for (var subject: subjects)
            subjectDtos.add(toSubjectDto(subject));
        return subjectDtos;
    }

    public SubjectDto findSubjectDtoById(Integer id) {
        Subject subject = subjectRepository.findById(id).orElse(new Subject());
        return toSubjectDto(subject);
    }

    public Subject findSubjectById(Integer id) {
        return subjectRepository.findById(id).orElse(new Subject());
    }
    
}
