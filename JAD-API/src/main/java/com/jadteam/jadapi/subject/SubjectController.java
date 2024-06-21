package com.jadteam.jadapi.subject;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SubjectController
 */
@RestController
@RequestMapping("/subjects")
public class SubjectController {
    
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("")
    public List<Subject> findAllSubjects() {
        return subjectService.findAllSubject();
    }

    @GetMapping("/{id}")
    public Subject findSubjectById(@PathVariable("id") Integer id) {
        return subjectService.findSubjectById(id);
    }

}
