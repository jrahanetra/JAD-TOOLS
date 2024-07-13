package com.jadteam.jadapi.subject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public SubjectDto toSubjectDto(Subject subject) {
        if (subject == null)
            throw new NullPointerException("L'objet subject est null.");
        SubjectDto subjectDto = new SubjectDto(subject.getSubjectId(), subject.getSubjectName(), subject.getHourNumber(), subject.getTeacher());
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
