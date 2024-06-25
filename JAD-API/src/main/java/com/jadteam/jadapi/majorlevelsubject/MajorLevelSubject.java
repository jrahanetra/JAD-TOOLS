package com.jadteam.jadapi.majorlevelsubject;

import com.jadteam.jadapi.level.Level;
import com.jadteam.jadapi.major.Major;
import com.jadteam.jadapi.subject.Subject;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

/**
 * MajorLevelSubject
 */
@Entity
public class MajorLevelSubject {
    
    @EmbeddedId
    private MajorLevelSubjectId majorLevelSubjectId;

    @ManyToOne
    @MapsId("majorId")
    @JoinColumn(name = "major_id")
    private Major major;

    @ManyToOne
    @MapsId("levelId")
    @JoinColumn(name = "level_id")
    private Level level;

    @ManyToOne
    @MapsId("subjectId")
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public MajorLevelSubject() {
    }

    public MajorLevelSubject(MajorLevelSubjectId majorLevelSubjectId, Major major, Level level, Subject subject) {
        this.majorLevelSubjectId = majorLevelSubjectId;
        this.major = major;
        this.level = level;
        this.subject = subject;
    }

    public MajorLevelSubjectId getMajorLevelSubjectId() {
        return majorLevelSubjectId;
    }

    public void setMajorLevelSubjectId(MajorLevelSubjectId id) {
        this.majorLevelSubjectId = id;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

}
