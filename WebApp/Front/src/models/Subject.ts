import Teacher from "./Teacher";

export default class Subject {
  subjectId: number;
  subjectName: string;
  hourNumber: number;
  teacherDto: Teacher;

  constructor(
    id: number,
    subjectName: string,
    hourNumber: number,
    teacher: Teacher
  ) {
    this.subjectId = id;
    this.subjectName = subjectName;
    this.hourNumber = hourNumber;
    this.teacherDto = teacher;
  }
}
