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
    private List<MajorLevelSubject> majorLevelSubjects = new ArrayList<>();

            
    public MajorLevelSubjectService(MajorLevelSubjectRepository majorLevelSubjectRepository,
                                    MajorService majorService, LevelService levelService,
                                    SubjectService subjectService) {
        this.majorLevelSubjectRepository = majorLevelSubjectRepository;
        this.majorService = majorService;
        this.levelService = levelService;
        this.subjectService = subjectService;

        MajorLevelSubjectId id1 = new MajorLevelSubjectId(3, 2, 1);
        MajorLevelSubjectId id2 = new MajorLevelSubjectId(3, 2, 2);
        MajorLevelSubjectId id3 = new MajorLevelSubjectId(3, 2, 3);
        MajorLevelSubjectId id4 = new MajorLevelSubjectId(3, 2, 4);
        MajorLevelSubjectId id5 = new MajorLevelSubjectId(3, 2, 5);
        MajorLevelSubjectId id6 = new MajorLevelSubjectId(2, 2, 1);
        MajorLevelSubjectId id7 = new MajorLevelSubjectId(2, 2, 2);
        MajorLevelSubjectId id8 = new MajorLevelSubjectId(2, 2, 3);
        MajorLevelSubjectId id9 = new MajorLevelSubjectId(2, 2, 4);
        MajorLevelSubjectId id10 = new MajorLevelSubjectId(2, 2, 5);
        Major ia = this.majorService.findMajorById(3);
        Major dev = this.majorService.findMajorById(2);
        Level l2 = this.levelService.findLevelById(2);
        Subject s1 = this.subjectService.findSubjectById(1);
        Subject s2 = this.subjectService.findSubjectById(2);
        Subject s3 = this.subjectService.findSubjectById(3);
        Subject s4 = this.subjectService.findSubjectById(4);
        Subject s5 = this.subjectService.findSubjectById(5);
        MajorLevelSubject mls1 = new MajorLevelSubject(id1, ia, l2, s1);
        MajorLevelSubject mls2 = new MajorLevelSubject(id2, ia, l2, s2);
        MajorLevelSubject mls3 = new MajorLevelSubject(id3, ia, l2, s3);
        MajorLevelSubject mls4 = new MajorLevelSubject(id4, ia, l2, s4);
        MajorLevelSubject mls5 = new MajorLevelSubject(id5, ia, l2, s5);
        MajorLevelSubject mls6 = new MajorLevelSubject(id6, dev, l2, s1);
        MajorLevelSubject mls7 = new MajorLevelSubject(id7, dev, l2, s2);
        MajorLevelSubject mls8 = new MajorLevelSubject(id8, dev, l2, s3);
        MajorLevelSubject mls9 = new MajorLevelSubject(id9, dev, l2, s4);
        MajorLevelSubject mls10 = new MajorLevelSubject(id10, dev, l2, s5);
        majorLevelSubjects.add(mls1);
        majorLevelSubjects.add(mls2);
        majorLevelSubjects.add(mls3);
        majorLevelSubjects.add(mls4);
        majorLevelSubjects.add(mls5);
        majorLevelSubjects.add(mls6);
        majorLevelSubjects.add(mls7);
        majorLevelSubjects.add(mls8);
        majorLevelSubjects.add(mls9);
        majorLevelSubjects.add(mls10);

        for (var mls: majorLevelSubjects)
            this.majorLevelSubjectRepository.save(mls);
    }

    public MajorLevelSubjectDto toMajorLevelSubjectDto(MajorLevelSubject majorLevelSubject) {
        if (majorLevelSubject == null)
            throw new NullPointerException("The MajorLevelSubject is null");
        MajorDto majorDto = majorService.toMajorDto(majorLevelSubject.getMajor());
        LevelDto levelDto = levelService.toLevelDto(majorLevelSubject.getLevel());
        SubjectDto subjectDto = subjectService.toSubjectDto(majorLevelSubject.getSubject());
        MajorLevelSubjectDto majorLevelSubjectDto = new MajorLevelSubjectDto(majorLevelSubject.getMajorLevelSubjectId(), majorDto, levelDto, subjectDto);
        return majorLevelSubjectDto;
    }

    public MajorLevelSubject toMajorLevelSubject(MajorLevelSubjectDto majorLevelSubjectDto) {
        if (majorLevelSubjectDto == null)
            throw new NullPointerException("The MajorLevelSubjectDto is null.");
        Major major = majorService.toMajor(majorLevelSubjectDto.majorDto());
        Level level = levelService.toLevel(majorLevelSubjectDto.levelDto());
        Subject subject = subjectService.toSubject(majorLevelSubjectDto.subjectDto());
        MajorLevelSubject majorLevelSubject = new MajorLevelSubject(majorLevelSubjectDto.majorLevelSubjectId(), major, level, subject);
        return majorLevelSubject;
    }

    public MajorLevelSubjectDto saveMajorLevelSubject(Integer majorId, Integer levelId, Integer subjectId) {
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
