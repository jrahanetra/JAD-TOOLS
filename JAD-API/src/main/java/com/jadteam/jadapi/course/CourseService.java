package com.jadteam.jadapi.course;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jadteam.jadapi.majorlevelsubject.MajorLevelSubject;
import com.jadteam.jadapi.majorlevelsubject.MajorLevelSubjectDto;
import com.jadteam.jadapi.majorlevelsubject.MajorLevelSubjectService;
import com.jadteam.jadapi.subject.Subject;
import com.jadteam.jadapi.subject.SubjectDto;
import com.jadteam.jadapi.subject.SubjectService;

import org.springframework.stereotype.Service;

/**
 * CourseService
 */
@Service
public class CourseService {

	private final CourseRepository courseRepository;
    private final SubjectService subjectService;
    private final MajorLevelSubjectService majorLevelSubjectService;

    public CourseService(CourseRepository courseRepository,
                         SubjectService subjectService,
                         MajorLevelSubjectService majorLevelSubjectService) {
        this.courseRepository = courseRepository;
        this.subjectService = subjectService;
        this.majorLevelSubjectService = majorLevelSubjectService;
    }

    public CourseDto toCourseDto(Course course) {
        if (course == null)
            throw new NullPointerException("The Course to convert is invalid.");
        Subject subject = subjectService.findSubjectById(course.getSubject().getSubjectId());
        SubjectDto subjectDto = subjectService.toSubjectDto(subject);
        CourseDto courseDto = new CourseDto(course.getCourseId(),
                                            course.getCourseDate(),
                                            course.getCourseBeginTime(),
                                            course.getCourseEndTime(),
                                            subjectDto);
        return courseDto;
    }

    public CourseDto saveCourse(Course course) {
        if (course == null)
            throw new NullPointerException("The Course is invalid.");
        courseRepository.save(course);
        return toCourseDto(course);
    }

    public List<CourseDto> findAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDto> courseDtos = new ArrayList<>();
        for (var course: courses)
            courseDtos.add(toCourseDto(course));
        return courseDtos;
    }

    public CourseDto findCourseById(Integer id) {
        if (id == null)
            throw new NullPointerException("The ID is invalid.");
        Course course = courseRepository.findById(id).orElse(new Course());
        CourseDto courseDto = toCourseDto(course);
        return courseDto;
    }

    public List<CourseDto> findAllCoursesByDate(LocalDate date) {
        if (date == null)
            throw new NullPointerException("The date is invalid.");
        List<Course> courses = courseRepository.findAllByCourseDate(date);
        List<CourseDto> courseDtos = new ArrayList<>();
        for (var course: courses)
            courseDtos.add(toCourseDto(course));
        return courseDtos;
    }

    public List<CourseDto> findAllCoursesBetweenDates(LocalDate beginDate,
                                                      LocalDate endDate) {
        if (beginDate == null)
            throw new NullPointerException("The begin date is invalid.");
        if (endDate == null)
            throw new NullPointerException("The end date is invalid.");
        if (!beginDate.isBefore(endDate))
            throw new DateTimeException("The date interval is invalid.");
        List<CourseDto> courses = new ArrayList<>();
        while (!endDate.plusDays(1).isEqual(beginDate)) {
            courses.addAll(findAllCoursesByDate(beginDate));
            beginDate = beginDate.plusDays(1);
        }
        return courses;
    } 

    public List<CourseDto> findAllCoursesByDateIntervalAndSubject(LocalDate beginDate,
                                                                  LocalDate endDate,
                                                                  Subject subject) {
        if (beginDate == null)
            throw new NullPointerException("The begin date is invalid.");
        if (endDate == null)
            throw new NullPointerException("The end date is invalid.");
        if (!beginDate.isBefore(endDate))
            throw new DateTimeException("The date interval is invalid.");
        if (subject == null)
            throw new NullPointerException("The subject is invalid.");
        List<CourseDto> courses = new ArrayList<>();
        courses = findAllCoursesBetweenDates(beginDate, endDate);
        List<CourseDto> courseOfSubject = courses.stream()
            .filter(c -> c.subjectDto().equals(subjectService.toSubjectDto(subject)))
            .toList();
        return courseOfSubject;
    }

    public List<CourseDto> findAllCoursesByDateAndLevelIdAndMajorId(LocalDate date,
                                                                    Integer levelId,
                                                                    Integer majorId) {
        if (date == null)
            throw new NullPointerException("The date is invalid.");
        if (levelId == null)
            throw new NullPointerException("The majorId is invalid.");
        if (majorId == null)
            throw new NullPointerException("The levelId is invalid.");
        List<MajorLevelSubjectDto> majorLevelSubjectDtos =
            majorLevelSubjectService.findAllMajorLevelSubjectByMajorIdAndLevelId(majorId, levelId);
        List<MajorLevelSubject> majorLevelSubjects = new ArrayList<>();
        for (var mls: majorLevelSubjectDtos)
            majorLevelSubjects.add(majorLevelSubjectService.toMajorLevelSubject(mls));
        List<CourseDto> courseDtos = new ArrayList<>();
        for (var mls: majorLevelSubjects)
            courseDtos.addAll(findAllCoursesByDateIntervalAndSubject(date, date.plusDays(6), mls.getSubject()));
        return courseDtos;
    }

}
