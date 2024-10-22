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

    public SubjectService(SubjectRepository subjectRepository, TeacherService teacherService) {
        this.subjectRepository = subjectRepository;
        this.teacherService = teacherService;
    }

    public SubjectDto toSubjectDto(Subject subject) {
        if (subject == null)
            throw new NullPointerException("The Subject to convert is invalid.");
        TeacherDto teacherDto = teacherService.toTeacherDto(subject.getTeacher());
        SubjectDto subjectDto = new SubjectDto(subject.getSubjectId(), subject.getSubjectName(), subject.getHourNumber(), teacherDto);
        return subjectDto;
    }

    public Subject toSubject(SubjectDto subjectDto) {
        if (subjectDto == null)
            throw new NullPointerException("The SubjectDto to convert is invalid.");
        Teacher teacher = teacherService.toTeacher(subjectDto.teacherDto());
        Subject subject = new Subject(subjectDto.subjectName(), subjectDto.hourNumber());
        subject.setSubjectId(subjectDto.subjectId());
        subject.setTeacher(teacher);
        return subject;
    }

    public SubjectDto saveSubject(Subject subject) {
        if (subject == null)
            throw new NullPointerException("The Subject is invalid.");
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
        if (id == null)
            throw new NullPointerException("The Subject ID is invalid.");
        Subject subject = subjectRepository.findById(id).orElse(new Subject());
        return toSubjectDto(subject);
    }

    public Subject findSubjectById(Integer id) {
        if (id == null)
            throw new NullPointerException("The Subject ID is invalid.");
        return subjectRepository.findById(id).orElse(new Subject());
    }
    
}
