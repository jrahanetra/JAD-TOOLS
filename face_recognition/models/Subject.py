
from models.Teacher import Teacher

class Subject: 
  def __init__(self, subjectId: int, subjectName: str, hourNumber: int, teacherDto: Teacher):
    self.subjectId = subjectId
    self.subjectName = subjectName
    self.hourNumber = hourNumber
    self.teacherDto = teacherDto
