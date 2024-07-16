import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Etudiant from "../../../models/Etudiant";
import StundentRow from "./StudentData";
import StudentCustomizeData from "../../../models/StudentCustomizeData";

function TableData(){

    const [studentCustomizeData, setCustomizeData] = useState<StudentCustomizeData[]>([]);

    useEffect(() => {
        fetch('http://localhost:8080/registrations')
        .then(response => response.json())
        .then((data) => {
            setCustomizeData(data);
        })
    }, [])

    const currentPath = window.location.pathname;
    const navigate = useNavigate();
    
    // Définir handleStudentClick en dehors de la déclaration JSX
    const handleStudentClick = (studentId: number) => {
        // navigate to other route for infoStudent
        navigate(`${currentPath}/${studentId}`)
    };

    return(
        <div className="container-tab">
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