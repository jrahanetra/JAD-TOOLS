import Subject from "./Subject";

export default class Course {
  courseId: number;
  courseDate: string;
  courseBeginTime: string;
  courseEndTime: string;
  subjectDto: Subject;

  constructor(
    courseId: number,
    courseDate: string,
    courseBegin: string,
    courseEnd: string,
    subject: Subject
  ) {
    this.courseId = courseId;
    this.courseDate = courseDate;
    this.courseBeginTime = courseBegin;
    this.courseEndTime = courseEnd;
    this.subjectDto = subject;
  }
} 