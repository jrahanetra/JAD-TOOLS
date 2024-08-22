package com.jadteam.jadapi.dataloader;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.jadteam.jadapi.course.Course;
import com.jadteam.jadapi.course.CourseRepository;
import com.jadteam.jadapi.level.Level;
import com.jadteam.jadapi.level.LevelRepository;
import com.jadteam.jadapi.major.Major;
import com.jadteam.jadapi.major.MajorRepository;
import com.jadteam.jadapi.majorlevelsubject.MajorLevelSubject;
import com.jadteam.jadapi.majorlevelsubject.MajorLevelSubjectId;
import com.jadteam.jadapi.majorlevelsubject.MajorLevelSubjectRepository;
import com.jadteam.jadapi.registration.Registration;
import com.jadteam.jadapi.registration.RegistrationId;
import com.jadteam.jadapi.registration.RegistrationRepository;
import com.jadteam.jadapi.student.Student;
import com.jadteam.jadapi.student.StudentRepository;
import com.jadteam.jadapi.subject.Subject;
import com.jadteam.jadapi.subject.SubjectRepository;
import com.jadteam.jadapi.teacher.Teacher;
import com.jadteam.jadapi.teacher.TeacherRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * InitialDataLoader
 */
@Component
public class InitialDataLoader implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final LevelRepository levelRepository;
    private final MajorRepository majorRepository;
    private final RegistrationRepository registrationRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final MajorLevelSubjectRepository majorLevelSubjectRepository;
    private final CourseRepository courseRepository;
    private static final Faker faker = new Faker(new Locale("fr-FR"));
    private static final FakeValuesService fvs = new FakeValuesService(new Locale("fr-FR"), new RandomService());
    private static List<Student> students = new ArrayList<>();
    private static List<Level> levels = new ArrayList<>();
    private static List<Major> majors = new ArrayList<>();
    private static List<Registration> registrations = new ArrayList<>();
    private static List<Teacher> teachers = new ArrayList<>();
    private static List<Subject> subjects = new ArrayList<>();
    private static List<MajorLevelSubject> majorLevelSubjects = new ArrayList<>();
    private static List<Course> l1Courses = new ArrayList<>();
    private static List<Course> l2Courses = new ArrayList<>();
    private static Random rand = new Random();

    public InitialDataLoader(StudentRepository studentRepository, LevelRepository levelRepository,
            MajorRepository majorRepository, RegistrationRepository registrationRepository,
            TeacherRepository teacherRepository, SubjectRepository subjectRepository,
            MajorLevelSubjectRepository majorLevelSubjectRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.levelRepository = levelRepository;
        this.majorRepository = majorRepository;
        this.registrationRepository = registrationRepository;
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
        this.majorLevelSubjectRepository = majorLevelSubjectRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\nInitialisation des données.\n");
        addStudents();
        addLevels();
        addMajors();
        addRegistrations();
        addTeachers();
        addSubjects();
        addMajorLevelSubjects();
        addCourses();
    }

    public void addStudents() {
        students.add(new Student("Antsa", "Rafanomezantsoa", "Ampitatafika", "antsa@gmail.com", "032 71 720 97"));
        students.add(new Student("Jason", "Rahanetra", "Ambatoroka", "jason@gmail.com", "038 77 667 97"));
        students.add(new Student("Dihary", "Rabearimanana", "Andranomena", "dihary@gmail.com", "034 09 241 65"));

        for (int i=0; i<100; i++) {
            String firstname = faker.name().firstName();
            String email = fvs.bothify(firstname+"###@gmail.com");
            Student s = new Student(firstname,
                                    faker.name().lastName(),
                                    faker.address().city(),
                                    email,
                                    faker.phoneNumber().phoneNumber());
            students.add(s);
        }
        studentRepository.saveAll(students);
    }

    public void addLevels() {
        levels.add(new Level("L1"));
        levels.add(new Level("L2"));
        levels.add(new Level("L3"));
        levels.add(new Level("M1"));
        levels.add(new Level("M2"));
        levelRepository.saveAll(levels);
    }

    public void addMajors() {
        majors.add(new Major("Infrastructure cloud et devops"));
        majors.add(new Major("Développement web et application mobile"));
        majors.add(new Major("Intelligence artificielle et big data"));
        majorRepository.saveAll(majors);
    }

    public void addRegistrations() {
        for (var student: students) {
            Major major = majors.get(rand.nextInt(majors.size()));
            Level level = levels.get(rand.nextInt(2));
            RegistrationId id = new RegistrationId(student.getStudentId(), major.getMajorId(), level.getLevelId());
            registrations.add(new Registration(id, student, major, level, 2024));
        }
        registrationRepository.saveAll(registrations);
    }

    public void addTeachers() {
        for (int i=0;i<7;i++) {
            teachers.add(new Teacher(faker.name().firstName(), faker.name().lastName()));
        }
        teacherRepository.saveAll(teachers);
    }

    public void addSubjects() {
        subjects.add(new Subject("Mathématiques 1", 60));
        subjects.add(new Subject("Français", 30));
        subjects.add(new Subject("Initiation à la programmation", 60));
        subjects.add(new Subject("Algorithmique et structure des données 1", 50));
        subjects.add(new Subject("Langage C", 60));
        subjects.add(new Subject("Mathématiques 2", 60));
        subjects.add(new Subject("Réseau", 60));
        subjects.add(new Subject("Recherches opérationnelles", 30));
        subjects.add(new Subject("C#", 50));
        subjects.add(new Subject("Algorithmique et stucture des données 2", 50));
        for (int i=0;i<subjects.size();i++)
            subjects.get(i).setTeacher(teachers.get(rand.nextInt(6)));
        subjectRepository.saveAll(subjects);
    }

    public void addMajorLevelSubjects() {
        for (int i=0;i<10;i++) {
            for (int j=0;j<3;j++) {
                Major major = majors.get(j);
                Level level = null;
                if (i < 5) level = levels.get(0);
                else level = levels.get(1);
                Subject subject = subjects.get(i);
                MajorLevelSubjectId id = new MajorLevelSubjectId(major.getMajorId(), level.getLevelId(), subject.getSubjectId());
                majorLevelSubjects.add(new MajorLevelSubject(id, major, level, subject));
            }
        }
        majorLevelSubjectRepository.saveAll(majorLevelSubjects);
    }

    public void addCourses() {
        LocalDate beginDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        while (!beginDate.getDayOfWeek().equals(DayOfWeek.MONDAY))
            beginDate = beginDate.minusDays(1);
        System.out.println(beginDate.toString());
        while (!endDate.getDayOfWeek().equals(DayOfWeek.SUNDAY))
            endDate = endDate.plusDays(1);
        System.out.println(endDate.toString());
        List<LocalDate> dates = beginDate.datesUntil(endDate).collect(Collectors.toList());
        System.out.println(dates.toString());
        List<List<LocalTime>> timeRanges = new ArrayList<>();
        timeRanges.add(Arrays.asList(LocalTime.of(8, 0), LocalTime.of(10, 0)));
        timeRanges.add(Arrays.asList(LocalTime.of(10, 0), LocalTime.of(12, 0)));
        timeRanges.add(Arrays.asList(LocalTime.of(13, 0), LocalTime.of(15, 0)));
        // timeRanges.add(Arrays.asList(LocalTime.of(15, 0), LocalTime.of(17, 0)));
        for (var date: dates) {
            int iB = rand.nextInt(3);
            System.out.println(iB);
            int iE = rand.nextInt(3);
            System.out.println(iE);
            if (iB > iE) {
                int tmp = iB;
                iB = iE;
                iE = tmp;
            }
            for (int i=iB; i<=iE; i++) {
                if (!date.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                    l1Courses.add(new Course(date, timeRanges.get(i).get(0), timeRanges.get(i).get(1)));
                    l2Courses.add(new Course(date, timeRanges.get(i).get(0), timeRanges.get(i).get(1)));
                }
            }
        }
        for (var course: l1Courses)
            course.setSubject(subjects.get(rand.nextInt(5)));
        for (var course: l2Courses)
            course.setSubject(subjects.get(rand.nextInt(5)+5));
        courseRepository.saveAll(l1Courses);
        courseRepository.saveAll(l2Courses);
    }
	
}