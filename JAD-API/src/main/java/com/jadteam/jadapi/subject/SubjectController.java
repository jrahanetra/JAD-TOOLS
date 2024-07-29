package com.jadteam.jadapi.subject;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("")
    public SubjectDto addSubject(@RequestBody Subject subject) {
        return subjectService.saveSubject(subject);
    }

    @GetMapping("")
    public List<SubjectDto> findAllSubjects() {
        return subjectService.findAllSubject();
    }

    @GetMapping("/{id}")
    public SubjectDto findSubjectById(@PathVariable("id") Integer id) {
        return subjectService.findSubjectDtoById(id);
    }

}
