class Student:
    def __init__(self, 
                 studentId: int, 
                 firstname: str, 
                 lastname: str, 
                 address: str, 
                 email: str, 
                 phoneNumber: str, 
                 sex: str,
                 birthday: str,
                 imageName: str):
        self.studentId = studentId
        self.firstname = firstname
        self.lastname = lastname
        self.address = address
        self.email = email
        self.phoneNumber = phoneNumber
        self.sex = sex
        self.birthday = birthday
        self.imageName = imageName
