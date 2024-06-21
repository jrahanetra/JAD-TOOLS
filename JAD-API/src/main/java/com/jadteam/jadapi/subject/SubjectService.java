package com.jadteam.jadapi.subject;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> findAllSubject() {
        return subjectRepository.findAll();
    }

    public Subject findSubjectById(Integer id) {
        return subjectRepository.findById(id).orElse(null);
    }
    
}
