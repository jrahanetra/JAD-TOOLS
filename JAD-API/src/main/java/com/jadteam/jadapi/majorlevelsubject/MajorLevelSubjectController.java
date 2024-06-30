package com.jadteam.jadapi.majorlevelsubject;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MajorLevelSubjectController
 */
@RestController
@RequestMapping("/majorlevelsubjects")
public class MajorLevelSubjectController {
    
    private final MajorLevelSubjectService majorLevelSubjectService;

    public MajorLevelSubjectController(MajorLevelSubjectService majorLevelSubjectService) {
        this.majorLevelSubjectService = majorLevelSubjectService;
    }

    @PostMapping("")
    public MajorLevelSubject addMajorLevelSubject(@RequestBody MajorLevelSubject majorLevelSubject) {
        return majorLevelSubjectService.saveMajorLevelSubject(majorLevelSubject);
    }
    
}
