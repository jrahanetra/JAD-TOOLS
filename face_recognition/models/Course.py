from models.Subject import Subject

class Course:
    def __init__(self, courseId: int, courseDate: str, courseBeginTime: str, courseEndTime: str, subjectDto: Subject):
        self.courseId = courseId
        self.courseDate = courseDate
        self.courseBeginTime = courseBeginTime
        self.courseEndTime = courseEndTime
        self.subjectDto = subjectDto
