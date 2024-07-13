package com.jadteam.jadapi.majorlevelsubject;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public MajorLevelSubjectDto addMajorLevelSubject(@RequestBody MajorLevelSubject majorLevelSubject) {
        return majorLevelSubjectService.saveMajorLevelSubject(majorLevelSubject);
    }

    @GetMapping("")
    public List<MajorLevelSubjectDto> getAllMajorLevelSubject() {
        return majorLevelSubjectService.findAllMajorLevelSubject();
    }

    @GetMapping("/s")
    public List<MajorLevelSubjectDto> getAllMajorLevelSubjectByMajorAndLevel(@RequestParam("majorId") Integer majorId,
                                                                             @RequestParam("levelId") Integer levelId) {
        return majorLevelSubjectService.findAllMajorLevelSubjectByMajorAndLevel(majorId, levelId);
    }
    
}
