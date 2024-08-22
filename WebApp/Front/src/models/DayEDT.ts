import Subject from "./Subject";

export default class DayEDT{
    // 1. Typage des propiétés d'un DayEDT.
    courseId : number;
    courseDate: string;
    courseBeginTime: string;
    courseEndTime: string;
    subjectDto: Subject;
    constructor(
        id: number,
        courseDate: string,
        courseBegin: string,
        courseEnd: string,
        subject: Subject
    ){
        this.courseId = id;
        this.courseDate = courseDate;
        this.courseBeginTime = courseBegin;
        this.courseEndTime = courseEnd;
        this.subjectDto = subject
    }
    
}