import React, { useEffect } from "react";
import { toast } from "react-toastify";
import StudentCustomizeDataCourse from "../models/StudentCustomizeDataCourses";

function fetchStudentCourses(
  studentcourses: StudentCustomizeDataCourse[],
  setStudentCourses: React.Dispatch<
    React.SetStateAction<StudentCustomizeDataCourse[]>
  >,
  params: string
) {
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/studentcourses/s?${params}`
        );
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
  }, [params]);
  return "Success Fetch"; // ou un indicateur de chargement ou un message de succès
}

export default fetchStudentCourses;
