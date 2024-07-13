package com.jadteam.jadapi.majorlevelsubject;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * MajorLevelSubjectRepository
 */
public interface MajorLevelSubjectRepository extends JpaRepository<MajorLevelSubject, MajorLevelSubjectId> {
    
    public List<MajorLevelSubject> findAllByMajorAndLevel();
}
