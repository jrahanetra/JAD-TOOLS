import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import StundentRow from "./StudentData";
import StudentCustomizeData from "../../../models/StudentCustomizeData";
import FecthRegistration from "../../../fecthAPI/FetchRegistrations";

type Props = {
  keyWordToFilter: string;
  filterByName: string;
  filterByLevel: string;
};

function TableData({ keyWordToFilter, filterByName, filterByLevel }: Props) {
  const [studentCustomizeData, setCustomizeData] = useState<
    StudentCustomizeData[]
  >([]);
  const [filteredData, setFilteredData] = useState<StudentCustomizeData[]>([]);

  FecthRegistration(studentCustomizeData, setCustomizeData);

  const currentPath = window.location.pathname;
  const navigate = useNavigate();

  const handleStudentClick = (studentId: number) => {
    navigate(`${currentPath}/${studentId}`);
  };

  useEffect(() => {
    let sortedData = [...studentCustomizeData];

    if (filterByName !== "" || filterByLevel !== "") {
      switch (filterByName) {
        case "↓By A":
          sortedData.sort((a, b) =>
            a.studentDto.lastname.localeCompare(b.studentDto.lastname)
          );
          break;
        case "↑By Z":
          sortedData.sort((a, b) =>
            b.studentDto.lastname.localeCompare(a.studentDto.lastname)
          );
          break;
        default:
          break;
      }

      switch (filterByLevel) {
        case "L2":
          sortedData = sortedData.filter((item) =>
            item.levelDto.levelName.localeCompare("L1")
          );
          break;
        case "L1":
          sortedData = sortedData.filter((item) =>
            item.levelDto.levelName.localeCompare("L2")
          );
          break;
        default:
          break;
      }
    } else {
      sortedData.sort(
        (a, b) => a.studentDto.studentId - b.studentDto.studentId
      );
    }

    setFilteredData(
      sortedData.filter((item) =>
        keyWordToFilter.toLowerCase() === ""
          ? true
          : item.studentDto.firstname
              .toLowerCase()
              .includes(keyWordToFilter.trim().toLowerCase()) ||
            item.studentDto.lastname
              .toLowerCase()
              .includes(keyWordToFilter.trim().toLowerCase()) ||
            item.studentDto.studentId === Number(keyWordToFilter) ||
            item.levelDto.levelName.toLowerCase() ===
              keyWordToFilter.trim().toLowerCase()
      )
    );
  }, [filterByName, filterByLevel, keyWordToFilter, studentCustomizeData]);

  return (
    <div className="container-tab">
      <ToastContainer />
      <table>
        <thead>
          <tr>
            <td className="td-head">Nº</td>
            <td className="td-head">LastName</td>
            <td className="td-head">FirstName</td>
            <td className="td-head">Level</td>
          </tr>
        </thead>
        <tbody>
          {filteredData.map((student) => (
            <StundentRow
              key={student.studentDto.studentId}
              borderColor="4px solid #257DE4"
              etudiant={student}
              onStudentClick={handleStudentClick}
            />
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default TableData;
