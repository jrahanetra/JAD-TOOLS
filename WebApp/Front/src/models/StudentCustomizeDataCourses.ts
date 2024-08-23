import Course from "./Course";
import Etudiant from "./Etudiant";
import Level from "./Level";
import Parcours from "./Parcours";
import StudentCourseId from "./StudentCourseId";

export default class StudentCustomizeDataCourse{
  studentCourseId: StudentCourseId;
  studentDto: Etudiant;
  courseDto: Course;
  attending: boolean;
  justificated: boolean;
  constructor(
    studentCourseId: StudentCourseId,
    studentDto: Etudiant,
    courseDto: Course,
    attending: boolean,
    justificated: boolean
  ){
    this.studentCourseId = studentCourseId;
    this.studentDto = studentDto;
    this.courseDto = courseDto;
    this.attending = attending;
    this.justificated = justificated;
  }
}
