import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import StundentRow from "./StudentData";
import StudentCustomizeData from "../../../models/StudentCustomizeData";
import FecthRegistration from "../../../fecthAPI/FetchRegistrations";

type Props = {
    keyWordToFilter: string,
    filterByName: string,
}
function TableData({ keyWordToFilter, filterByName}: Props) {

    const [studentCustomizeData, setCustomizeData] = useState<StudentCustomizeData[]>([]);

    FecthRegistration(studentCustomizeData, setCustomizeData)

    const currentPath = window.location.pathname;
    const navigate = useNavigate();

    // Définir handleStudentClick en dehors de la déclaration JSX
    const handleStudentClick = (studentId: number) => {
        // navigate to other route for infoStudent
        navigate(`${currentPath}/${studentId}`)
    };
    if (filterByName !== ""){
        switch (filterByName){
            case "↓A":
                setCustomizeData(studentCustomizeData.sort((a,b) => a.studentDto.lastname.localeCompare(b.studentDto.lastname)))
                break
            case "↑Z":
                setCustomizeData(studentCustomizeData.sort((a,b) => b.studentDto.lastname.localeCompare(a.studentDto.lastname)))
                break
            default: 
                break
        }
    }

    return (
        <div className="container-tab">
            <ToastContainer />
            <table>
                <thead>
                    <tr>
                        <td className="td-head">Roll no</td>
                        <td className="td-head">LastName</td>
                        <td className="td-head">FirstName</td>
                        <td className="td-head">Level</td>
                    </tr>
                </thead>
                <tbody>
                    {studentCustomizeData
                        .sort((a, b) => a.studentDto.studentId - b.studentDto.studentId)
                        .filter(item =>
                            keyWordToFilter.toLowerCase() === "" ? item : 
                                item.studentDto.firstname.toLowerCase().includes(keyWordToFilter.trim().toLowerCase()) ||
                                item.studentDto.lastname.toLowerCase().includes(keyWordToFilter.trim().toLowerCase())  ||
                                item.studentDto.studentId === Number(keyWordToFilter) || 
                                item.levelDto.levelName.toLowerCase() === keyWordToFilter.trim().toLowerCase()            
                        )
                        .map(student => (
                            <StundentRow
                                key={student.studentDto.studentId}
                                borderColor="4px solid #257DE4"
                                etudiant={student}
                                onStudentClick={handleStudentClick} />
                        ))}
                </tbody>
            </table>
        </div>
    )
}
export default TableData;