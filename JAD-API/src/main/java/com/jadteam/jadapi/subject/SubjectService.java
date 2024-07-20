package com.jadteam.jadapi.subject;

import java.util.ArrayList;
import java.util.List;

import com.jadteam.jadapi.teacher.TeacherDto;
import com.jadteam.jadapi.teacher.TeacherService;

import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final TeacherService teacherService;

    public SubjectService(SubjectRepository subjectRepository, TeacherService teacherService) {
        this.subjectRepository = subjectRepository;
        this.teacherService = teacherService;
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

    public SubjectDto findSubjectById(Integer id) {
        Subject subject = subjectRepository.findById(id).orElse(new Subject());
        return toSubjectDto(subject);
    }
    
}
