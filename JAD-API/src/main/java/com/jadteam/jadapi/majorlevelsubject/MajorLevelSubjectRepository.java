package com.jadteam.jadapi.majorlevelsubject;

import java.util.List;

import com.jadteam.jadapi.level.Level;
import com.jadteam.jadapi.major.Major;
import com.jadteam.jadapi.subject.Subject;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * MajorLevelSubjectRepository
 */
public interface MajorLevelSubjectRepository extends JpaRepository<MajorLevelSubject, MajorLevelSubjectId> {
    
    public List<MajorLevelSubject> findAllByMajorAndLevel(Major major, Level level);

    public List<MajorLevelSubject> findAllBySubject(Subject subject);
}
