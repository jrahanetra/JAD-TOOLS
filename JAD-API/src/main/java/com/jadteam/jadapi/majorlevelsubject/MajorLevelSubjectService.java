package com.jadteam.jadapi.majorlevelsubject;

import org.springframework.stereotype.Service;

/**
 * MajorLevelSubjectService
 */
@Service
public class MajorLevelSubjectService {
    
    private final MajorLevelSubjectRepository majorLevelSubjectRepository;

    public MajorLevelSubjectService(MajorLevelSubjectRepository majorLevelSubjectRepository) {
        this.majorLevelSubjectRepository = majorLevelSubjectRepository;
    }

    public MajorLevelSubject saveMajorLevelSubject(MajorLevelSubject majorLevelSubject) {
        return majorLevelSubjectRepository.save(majorLevelSubject);
    }

}
