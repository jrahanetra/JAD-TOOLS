import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Etudiant from "../../../models/Etudiant";
import StundentRow from "./StudentData";

function TableData(){

    const [etudiantList, setEtudiant] = useState<Etudiant[]>([]);

    useEffect(() => {
        fetch('http://localhost:8080/students')
        .then(response => response.json())
        .then((student) => {
            setEtudiant(student);
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
                    <td className="td-head">Name</td>
                    <td className="td-head">Level</td>
                    <td className="td-head">Semester</td>
                </tr>
            </table>
            <div className="div-row-table">
                <table>
                    {etudiantList.map(student => (
                        <StundentRow key={student.id} etudiant={student} onStudentClick={handleStudentClick}/>
                    ))}
                    {etudiantList.map(student => (
                        <StundentRow key={student.id} etudiant={student} onStudentClick={handleStudentClick}/>
                    ))}{etudiantList.map(student => (
                        <StundentRow key={student.id} etudiant={student} onStudentClick={handleStudentClick}/>
                    ))}
                    {etudiantList.map(student => (
                        <StundentRow key={student.id} etudiant={student} onStudentClick={handleStudentClick}/>
                    ))}
                    {etudiantList.map(student => (
                        <StundentRow key={student.id} etudiant={student} onStudentClick={handleStudentClick}/>
                    ))}
                    {etudiantList.map(student => (
                        <StundentRow key={student.id} etudiant={student} onStudentClick={handleStudentClick}/>
                    ))}
                    {etudiantList.map(student => (
                        <StundentRow key={student.id} etudiant={student} onStudentClick={handleStudentClick}/>
                    ))}
                </table>
            </div>
        </div>
    )
}
export default TableData;