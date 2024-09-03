import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import StudentCustomizeDataCourse from "../models/StudentCustomizeDataCourses";
import StudentCustomizeData from "../models/StudentCustomizeData";

function usePresencePerLevel(level: string): number {
  const [studentCourses, setStudentCourses] = useState<
    StudentCustomizeDataCourse[]
  >([]);
  let tempFreqPres = 0;

  let postData = {};
  switch (level) {
    case "L1":
      postData = {
        levelId: 1,
      };
      break;
    case "L2":
      postData = {
        levelId: 2,
      };
      break;
    default:
      break;
  }
  const params = new URLSearchParams(postData).toString();
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/studentcourses/l?${params}`
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
  }, []);

  studentCourses.forEach((studentCourse) => {
    if (studentCourse.attending === true) {
      tempFreqPres += 1;
    }
  });
  return tempFreqPres / studentCourses.length;
}

export default usePresencePerLevel;
