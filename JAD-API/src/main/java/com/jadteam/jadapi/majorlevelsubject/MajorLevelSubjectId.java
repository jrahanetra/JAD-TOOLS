package com.jadteam.jadapi.majorlevelsubject;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * MajorLevelSubjectKey
 */
@Embeddable
public class MajorLevelSubjectId implements Serializable {
    
	@Column(name = "major_id")
    private Integer majorId;

    @Column(name = "level_id")
    private Integer levelId;

    @Column(name = "subject_id")
    private Integer subjectId;

    public MajorLevelSubjectId() {
    }

    public MajorLevelSubjectId(Integer majorId, Integer levelId, Integer subjectId) {
        this.majorId = majorId;
        this.levelId = levelId;
        this.subjectId = subjectId;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

}
