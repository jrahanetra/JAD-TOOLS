import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import StudentCustomizeDataCourse from "../models/StudentCustomizeDataCourses";
import StudentCustomizeData from "../models/StudentCustomizeData";

function usePresencePerLevel(level: string): number {
  const [students, setStudents] = useState<StudentCustomizeData[]>([]);
  const [idStudent, setIdStudent] = useState<number | null>(null);
  const [i, setI] = useState<number>(0)
  const [studentCourses, setStudentCourses] = useState<
    StudentCustomizeDataCourse[]
  >([]);
  const [tempFreqPres, setTempFreqPres] = useState<number>(0);

  useEffect(() => {
    const fetchStudents = async () => {
      try {
        const response = await fetch(`http://localhost:8080/registrations`);
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        const data = await response.json();
        setStudents(data);
      } catch (error) {
        toast.error(`Error : ${error}`);
      }
    };
    fetchStudents();
  }, []);

  useEffect(() => {
    const filteredList = students.filter(
      (student) => student.levelDto.levelName === level
    );

    if (filteredList.length > 0) {
      // On utilise uniquement le premier étudiant trouvé
      setIdStudent(filteredList[0 + i].studentDto.studentId);
      setI(prev => prev + 1)
    }
  }, [students, level]);

  useEffect(() => {
    if (idStudent !== null) {
      const fetchStudentCourses = async () => {
        try {
          const response = await fetch(
            `http://localhost:8080/studentcourses/s?studentId=${idStudent}`
          );
          if (!response.ok) {
            throw new Error("Network response was not ok");
          }
          const data = await response.json();
          setStudentCourses(data);

          // Calculer la fréquence de présence ici en fonction des données des cours
          const presenceCount = studentCourses.filter(
            (course) => course.attending === true
          ).length;
          setTempFreqPres(presenceCount);
        } catch (error) {
          toast.error(`Error : ${error}`);
        }
      };
      fetchStudentCourses();
    }
  }, [idStudent]);

  return tempFreqPres;
}

export default usePresencePerLevel;
