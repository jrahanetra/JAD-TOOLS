import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import StudentCustomizeDataCourse from "../models/StudentCustomizeDataCourses";

function calculatePresence(
  sex: string
) : number {
  const [studentCourses, setStudentCourses] = useState<
    StudentCustomizeDataCourse[]
  >([]);
  let tempFreqPres = 0
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(`http://localhost:8080/studentcourses`);
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        const data = await response.json();
        setStudentCourses(data);
      } catch (error) {
        toast.error(`Error : ${error}`);
      }
    };
    fetchData();
  }, []);

  studentCourses.forEach((studentCourse) => {
    if(studentCourse.studentDto.sex === sex){
      tempFreqPres += 1;
    }
  })
  return tempFreqPres / studentCourses.length
}

export default calculatePresence;