package com.jadteam.jadapi.majorlevelsubject;

import com.jadteam.jadapi.level.LevelDto;
import com.jadteam.jadapi.major.MajorDto;
import com.jadteam.jadapi.subject.SubjectDto;

/**
 * MajorLevelSubjectDto
 */
public record MajorLevelSubjectDto(MajorLevelSubjectId majorLevelSubjectId,
                                   MajorDto majorDto,
                                   LevelDto levelDto,
                                   SubjectDto subjectDto) {
}
