import React, { useEffect, useState } from "react";
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

    return(
        <div className="container-tab">
            <table>
                <tr>
                    <td className="td-head">Roll no</td>
                    <td className="td-head">Name</td>
                    <td className="td-head">Year</td>
                    <td className="td-head">Semester</td>
                    <td className="td-head">Course</td>
                    <td className="td-head">Date</td>
                    <td className="td-head">Hour</td>
                    <td className="td-head">Attend</td>
                </tr>
            </table>
            <div className="div-row-table">
                <table>
                    
                    {etudiantList.map(student => (
                        <StundentRow key={student.studentId} etudiant={student}/>
                    ))}{etudiantList.map(student => (
                        <StundentRow key={student.studentId} etudiant={student}/>
                    ))}{etudiantList.map(student => (
                        <StundentRow key={student.studentId} etudiant={student}/>
                    ))}{etudiantList.map(student => (
                        <StundentRow key={student.studentId} etudiant={student}/>
                    ))}{etudiantList.map(student => (
                        <StundentRow key={student.studentId} etudiant={student}/>
                    ))}
                </table>              
            </div>
        </div>
    )
}
export default TableData;