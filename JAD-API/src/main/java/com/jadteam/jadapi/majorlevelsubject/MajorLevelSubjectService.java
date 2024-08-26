package com.jadteam.jadapi.majorlevelsubject;

import java.util.ArrayList;
import java.util.List;

import com.jadteam.jadapi.level.Level;
import com.jadteam.jadapi.level.LevelDto;
import com.jadteam.jadapi.level.LevelService;
import com.jadteam.jadapi.major.Major;
import com.jadteam.jadapi.major.MajorDto;
import com.jadteam.jadapi.major.MajorService;
import com.jadteam.jadapi.subject.Subject;
import com.jadteam.jadapi.subject.SubjectDto;
import com.jadteam.jadapi.subject.SubjectService;

import org.springframework.stereotype.Service;

/**
 * MajorLevelSubjectService
 */
@Service
public class MajorLevelSubjectService {

    private final MajorLevelSubjectRepository majorLevelSubjectRepository;
    private final MajorService majorService;
    private final LevelService levelService;
    private final SubjectService subjectService;
            
    public MajorLevelSubjectService(MajorLevelSubjectRepository majorLevelSubjectRepository,
                                    MajorService majorService, LevelService levelService,
                                    SubjectService subjectService) {
        this.majorLevelSubjectRepository = majorLevelSubjectRepository;
        this.majorService = majorService;
        this.levelService = levelService;
        this.subjectService = subjectService;
    }

    public MajorLevelSubjectDto toMajorLevelSubjectDto(MajorLevelSubject majorLevelSubject) {
        if (majorLevelSubject == null)
            throw new NullPointerException("The MajorLevelSubject to convert is invalid.");
        MajorDto majorDto = majorService.toMajorDto(majorLevelSubject.getMajor());
        LevelDto levelDto = levelService.toLevelDto(majorLevelSubject.getLevel());
        SubjectDto subjectDto = subjectService.toSubjectDto(majorLevelSubject.getSubject());
        MajorLevelSubjectDto majorLevelSubjectDto = new MajorLevelSubjectDto(majorLevelSubject.getMajorLevelSubjectId(), majorDto, levelDto, subjectDto);
        return majorLevelSubjectDto;
    }

    public MajorLevelSubject toMajorLevelSubject(MajorLevelSubjectDto majorLevelSubjectDto) {
        if (majorLevelSubjectDto == null)
            throw new NullPointerException("The MajorLevelSubjectDto to convert is invalid.");
        Major major = majorService.toMajor(majorLevelSubjectDto.majorDto());
        Level level = levelService.toLevel(majorLevelSubjectDto.levelDto());
        Subject subject = subjectService.toSubject(majorLevelSubjectDto.subjectDto());
        MajorLevelSubject majorLevelSubject = new MajorLevelSubject(majorLevelSubjectDto.majorLevelSubjectId(), major, level, subject);
        return majorLevelSubject;
    }

    public MajorLevelSubjectDto saveMajorLevelSubject(Integer majorId, Integer levelId, Integer subjectId) {
        if (majorId == null)
            throw new NullPointerException("The Major ID is invalid.");
        if (levelId == null)
            throw new NullPointerException("The Level ID is invalid.");
        if (subjectId == null)
            throw new NullPointerException("The Subject ID is invalid.");
        MajorLevelSubjectId majorLevelSubjectId = new MajorLevelSubjectId(majorId, levelId, subjectId);
        Major major = majorService.findMajorById(majorId);
        Level level = levelService.findLevelById(levelId);
        Subject subject = subjectService.findSubjectById(subjectId);
        MajorLevelSubject majorLevelSubject = new MajorLevelSubject(majorLevelSubjectId, major, level, subject);
        majorLevelSubjectRepository.save(majorLevelSubject);
        return toMajorLevelSubjectDto(majorLevelSubject);
    }

    public List<MajorLevelSubjectDto> findAllMajorLevelSubject() {
        List<MajorLevelSubject> majorLevelSubjects = majorLevelSubjectRepository.findAll();
        List<MajorLevelSubjectDto> majorLevelSubjectDtos = new ArrayList<>();
        for (var majorLevelSubject: majorLevelSubjects)
            majorLevelSubjectDtos.add(toMajorLevelSubjectDto(majorLevelSubject));
        return majorLevelSubjectDtos;
    }

    public List<MajorLevelSubjectDto> findAllMajorLevelSubjectByMajorIdAndLevelId(Integer majorId,
                                                                              Integer levelId) {
        if (majorId == null)
            throw new NullPointerException("The Major ID is invalid.");
        if (levelId == null)
            throw new NullPointerException("The Level ID is invalid.");
        Major major = majorService.findMajorById(majorId);
        Level level = levelService.findLevelById(levelId);
        if (major == null || level == null)
            return new ArrayList<>();
        List<MajorLevelSubject> majorLevelSubjects = majorLevelSubjectRepository.findAllByMajorAndLevel(major, level);
        List<MajorLevelSubjectDto> majorLevelSubjectDtos = new ArrayList<>();
        for (var majorLevelSubject: majorLevelSubjects)
            majorLevelSubjectDtos.add(toMajorLevelSubjectDto(majorLevelSubject));
        return majorLevelSubjectDtos;
    }

}
