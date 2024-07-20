package com.jadteam.jadapi.majorlevelsubject;

import java.util.ArrayList;
import java.util.List;

import com.jadteam.jadapi.level.Level;
import com.jadteam.jadapi.level.LevelDto;
import com.jadteam.jadapi.level.LevelService;
import com.jadteam.jadapi.major.Major;
import com.jadteam.jadapi.major.MajorDto;
import com.jadteam.jadapi.major.MajorService;
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
        if (majorLevelSubject.equals(null))
            throw new NullPointerException("L'objet MajorLevelSubject à convertir est null");
        MajorDto majorDto = majorService.toMajorDto(majorLevelSubject.getMajor());
        LevelDto levelDto = levelService.toLevelDto(majorLevelSubject.getLevel());
        SubjectDto subjectDto = subjectService.toSubjectDto(majorLevelSubject.getSubject());
        MajorLevelSubjectDto majorSubjectDto = new MajorLevelSubjectDto(majorLevelSubject.getMajorLevelSubjectId(), majorDto, levelDto, subjectDto);
        return majorSubjectDto;
    }

    public MajorLevelSubjectDto saveMajorLevelSubject(MajorLevelSubject majorLevelSubject) {
        if (majorLevelSubject == null)
            throw new NullPointerException("L'objet MajorLevelSubject ne peut pas être sauvegardé car il est null.");
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

    public List<MajorLevelSubjectDto> findAllMajorLevelSubjectByMajorAndLevel(Integer majorId,
                                                                              Integer levelId) {
        if (majorId == null)
            throw new NullPointerException("L'ID du parcours est invalide.");
        if (levelId == null)
            throw new NullPointerException("L'ID du niveau est invalide.");
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
