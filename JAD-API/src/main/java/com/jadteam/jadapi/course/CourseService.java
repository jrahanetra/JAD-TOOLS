package com.jadteam.jadapi.course;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private static List<Course> courses = new ArrayList<>();

    static {
        LocalDate d1 = LocalDate.of(2024, 8, 26);
        LocalDate d2 = LocalDate.of(2024, 8, 27);
        LocalDate d3 = LocalDate.of(2024, 8, 28);
        LocalDate d4 = LocalDate.of(2024, 8, 29);
        LocalDate d5 = LocalDate.of(2024, 8, 30);
        LocalTime t1 = LocalTime.of(8, 0);
        LocalTime t2 = LocalTime.of(10, 0);
        LocalTime t3 = LocalTime.of(12, 0);
        LocalTime t4 = LocalTime.of(13, 0);
        LocalTime t5 = LocalTime.of(15, 0);
        LocalTime t6 = LocalTime.of(17, 0);
        Course c1 = new Course(d1, t1, t2);
        Course c2 = new Course(d1, t4, t5);
        Course c3 = new Course(d2, t1, t2);
        Course c4 = new Course(d2, t2, t3);
        Course c5 = new Course(d2, t4, t5);
        Course c6 = new Course(d2, t5, t6);
        Course c7 = new Course(d3, t1, t2);
        Course c8 = new Course(d3, t2, t3);
        Course c9 = new Course(d4, t2, t3);
        Course c10 = new Course(d5, t1, t2);
        Course c11 = new Course(d5, t4, t5);
        courses.add(c1);
        courses.add(c2);
        courses.add(c3);
        courses.add(c4);
        courses.add(c5);
        courses.add(c6);
        courses.add(c7);
        courses.add(c8);
        courses.add(c9);
        courses.add(c10);
        courses.add(c11);
    }

    public CourseService(CourseRepository courseRepository,
                         SubjectService subjectService,
                         MajorLevelSubjectService majorLevelSubjectService) {
        this.courseRepository = courseRepository;
        this.subjectService = subjectService;
        this.majorLevelSubjectService = majorLevelSubjectService;
        Random rand = new Random();
        for (var course: courses) {
            course.setSubject(this.subjectService.findSubjectById(rand.nextInt(4)+1));
            this.courseRepository.save(course);
        }
    }

    public CourseDto toCourseDto(Course course) {
        if (course == null)
            throw new NullPointerException("L'objet Course est invalide.");
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
            throw new NullPointerException("L'object Course est invalide.");
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
            throw new NullPointerException("L'ID du cours fourni est invalide.");
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
            throw new DateTimeException("L'intervalle est invalide.");
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
