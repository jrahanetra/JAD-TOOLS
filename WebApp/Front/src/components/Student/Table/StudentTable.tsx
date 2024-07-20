import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Etudiant from "../../../models/Etudiant";
import StundentRow from "./StudentData";
import StudentCustomizeData from "../../../models/StudentCustomizeData";

function TableData(){

    const [studentCustomizeData, setCustomizeData] = useState<StudentCustomizeData[]>([]);

    const fetchData = async () => {
        try {
          const response = await fetch('http://localhost:8080/registrations');
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          const data = await response.json();
          setCustomizeData(data);
        } catch (error) {
          toast.error(`Error: ${error}`);
        } 
      };
    
      useEffect(() => {
        fetchData();
      }, []);

    const currentPath = window.location.pathname;
    const navigate = useNavigate();
    
    // Définir handleStudentClick en dehors de la déclaration JSX
    const handleStudentClick = (studentId: number) => {
        // navigate to other route for infoStudent
        navigate(`${currentPath}/${studentId}`)
    };

    return(
        <div className="container-tab">
            <ToastContainer />
            <table>
                <tr>
                    <td className="td-head">Roll no</td>
                    <td className="td-head">LastName</td>
                    <td className="td-head">FirstName</td>
                    <td className="td-head">Level</td>
                </tr>
            </table>
            <div className="div-row-table">
                <table>
                    {studentCustomizeData.sort( (a,b) =>  a.studentDto.studentId - b.studentDto.studentId).map(student => (
                        <StundentRow key={student.studentDto.studentId} etudiant={student} onStudentClick={handleStudentClick}/>
                    ))}
                </table>
            </div>
        </div>
    )
}
export default TableData;